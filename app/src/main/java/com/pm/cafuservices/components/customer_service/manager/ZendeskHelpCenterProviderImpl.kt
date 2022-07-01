package com.pm.cafuservices.components.customer_service.manager

import android.content.Context
import android.util.Log
import com.pm.cafuservices.components.customer_service.HelpCentreProvider
import com.pm.cafuservices.components.customer_service.manager.model.*
import com.zendesk.service.ErrorResponse
import com.zendesk.service.ZendeskCallback
import kotlinx.coroutines.*
import zendesk.core.AnonymousIdentity
import zendesk.core.Zendesk
import zendesk.support.Article
import zendesk.support.HelpCenterProvider
import zendesk.support.Section
import zendesk.support.Support
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

@OptIn(ExperimentalCoroutinesApi::class)
class ZendeskHelpCenterProviderImpl(
    val context: Context
) : HelpCentreProvider {

    private var provider: HelpCenterProvider? = null
    private val job = SupervisorJob()
    private val ioScope by lazy { CoroutineScope(job + Dispatchers.IO) }

    companion object {
        const val HC_ZENDESK_URL = "https://cafu.zendesk.com"
        const val HC_ZENDESK_APP_ID = "e2925718919635c3e33f1ebc6e4a2ef14348d1b576a815bf"
        const val HC_ZENDESK_AUTH_CLIENT_ID = "mobile_sdk_client_975eb402a1fe511124a3"
    }

    override fun init() {
        Zendesk.INSTANCE.init(
            context, HC_ZENDESK_URL,
            HC_ZENDESK_APP_ID,
            HC_ZENDESK_AUTH_CLIENT_ID
        )
    }

    override fun setIdentity(userIdentity: UserIdentity) {
        val name: String = userIdentity.firstName +
                if (userIdentity.lastName.isNotEmpty()
                ) " " + userIdentity.lastName else ""

        // prepare identity
        val identity = AnonymousIdentity.Builder()
            .withNameIdentifier(name)
            .withEmailIdentifier(userIdentity.email)
            .build()

        // set identity into zendesk
        Zendesk.INSTANCE.setIdentity(identity)

        // set instance to support
        Support.INSTANCE.init(Zendesk.INSTANCE)

        // Set provider
        provider = Support.INSTANCE.provider()?.helpCenterProvider()
    }

    override fun <T> getVisitorInfo(userIdentity: UserIdentity): T {
        TODO("Not yet implemented")
    }

    /**
     * Get section from zendesk and warp that function with coroutine and pass as lamda function
     */
    override fun getSection(result: (Result<List<HCSection>>) -> Unit) {
        ioScope.launch {
            try {
                result(Result.Success(getSectionFromZendesk()))
            } catch (exception: Exception) {
                // Handles exceptions here.
                // Prints "java.util.concurrent.CancellationException: Continuation
                Log.d("TAG", "getSection: $exception")
                result(Result.Error(exception))
            }
        }
    }

    override fun getArticle(id: Long, result: (Result<List<HCArticle>>) -> Unit) {
        ioScope.launch {
            try {
                result(Result.Success(getArticleFromZendesk(id)))
            } catch (exception: Exception) {
                // Handles exceptions here.
                // Prints "java.util.concurrent.CancellationException: Continuation
                Log.d("TAG", "getArticle: $exception")
                result(Result.Error(exception))
            }
        }
    }

    @OptIn(FlowPreview::class)
    override fun getSectionWithArticle(result: (Result<List<HCSectionWithArticle>>) -> Unit) {
        result(Result.InProgress)
        ioScope.launch {
            try {
                val sectionWithArticleList: List<HCSectionWithArticle> = getSectionFromZendesk()
                    .map { hcSection ->
                        val articleList = getArticleFromZendesk(hcSection.id)
                        val hcSectionWithArticle = HCSectionWithArticle(
                            hcSection.id,
                            hcSection.title,
                            articleList
                        )
                        hcSectionWithArticle
                    }
                result(Result.Success(sectionWithArticleList))
            } catch (exception: Exception) {
                // Handles exceptions here.
                // Prints "java.util.concurrent.CancellationException: Continuation
                Log.d("TAG", "getSectionWithArticle: $exception")
                result(Result.Error(exception))
            }
        }

    }

    @ExperimentalCoroutinesApi
    private suspend fun getSectionFromZendesk(): List<HCSection> =
        suspendCoroutine { continuation ->

            provider?.let { helpCenterProvider ->
                val zendeskCallBackFlow = object : ZendeskCallback<List<Section>>() {
                    override fun onSuccess(sections: List<Section>) {
                        val sectionList = sections.map { section ->
                            HCSection(section.id!!, section.name!!)
                        }
                        continuation.resume(sectionList)
                    }

                    override fun onError(errorResponse: ErrorResponse) {
                        Log.d("TAG", "onError: ${errorResponse.reason}")
                        continuation.resumeWithException(Exception(errorResponse.reason))
                    }
                }

                helpCenterProvider.getSections(
                    Category.FAQ.id,
                    zendeskCallBackFlow
                )
            } ?: continuation.resume(emptyList())
        }

    @ExperimentalCoroutinesApi
    private suspend fun getArticleFromZendesk(id: Long): List<HCArticle> =
        suspendCoroutine { continuation ->
            provider?.let { helpCenterProvider ->
                val zendeskCallBackFlow = object : ZendeskCallback<List<Article>>() {
                    override fun onSuccess(articles: List<Article>) {
                        val articleList = articles.map { article ->
                            HCArticle(
                                article.id,
                                article.title ?: "",
                                article.body ?: "",
                                article.htmlUrl ?: ""
                            )
                        }
                        continuation.resume(articleList)
                    }

                    override fun onError(errorResponse: ErrorResponse) {
                        Log.d("TAG", "onError: ${errorResponse.reason}")
                        continuation.resumeWithException(Exception(errorResponse.reason))
                    }
                }

                helpCenterProvider.getArticles(
                    id,
                    zendeskCallBackFlow
                )
            } ?: continuation.resume(emptyList())
        }
}

