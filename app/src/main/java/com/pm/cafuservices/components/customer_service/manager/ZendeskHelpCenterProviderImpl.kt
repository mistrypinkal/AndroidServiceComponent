package com.pm.cafuservices.components.customer_service.manager

import android.content.Context
import android.util.Log
import com.pm.cafuservices.components.customer_service.HelpCentreProvider
import com.pm.cafuservices.components.customer_service.manager.model.*
import com.zendesk.service.ErrorResponse
import com.zendesk.service.ZendeskCallback
import zendesk.core.AnonymousIdentity
import zendesk.core.Zendesk
import zendesk.support.Article
import zendesk.support.HelpCenterProvider
import zendesk.support.Section
import zendesk.support.Support

class ZendeskHelpCenterProviderImpl(
    val context: Context
) : HelpCentreProvider {

    private var provider: HelpCenterProvider? = null

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

    override fun getSection(result: (List<HCSection>) -> Unit) {
        getSectionFromZendesk { sectionList ->
            result(sectionList)
        }
    }

    override fun getArticle(id: Long, result: (List<HCArticle>) -> Unit) {
        getArticleFromZendesk(id) { articleList ->
            result(articleList)
        }
    }

    override fun getSectionWithArticle(): List<HCSectionWithArticle> {
        val sectionWithArticleList: MutableList<HCSectionWithArticle> = mutableListOf()
        getSectionFromZendesk { sectionList ->
            sectionList.forEach { hcSection ->
                val hcSectionWithArticle = HCSectionWithArticle(
                    hcSection.id,
                    hcSection.title
                )
                getArticleFromZendesk(hcSection.id) { articleList ->
                    hcSectionWithArticle.articlesList = articleList
                    sectionWithArticleList.add(hcSectionWithArticle)
                }
            }
        }
        return emptyList()
    }

    private fun getSectionFromZendesk(result: (List<HCSection>) -> Unit) {
        val sectionList: MutableList<HCSection> = mutableListOf()
        provider?.let { helpCenterProvider ->
            helpCenterProvider.getSections(
                Category.FAQ.id,
                object : ZendeskCallback<List<Section>>() {
                    override fun onSuccess(sections: List<Section>) {
                        for (section in sections) {
                            section.let {
                                val hcSections = HCSection(section.id!!, section.name!!)
                                sectionList.add(hcSections)
                            }
                        }
                        result(sectionList)
                    }

                    override fun onError(errorResponse: ErrorResponse) {
                        Log.d("TAG", "onError: ${errorResponse.reason}")
                        result(sectionList)
                    }
                })
        } ?: result(sectionList)
    }

    private fun getArticleFromZendesk(id: Long, result: (List<HCArticle>) -> Unit) {
        val articleList: MutableList<HCArticle> = mutableListOf()
        provider?.getArticles(
            id, object : ZendeskCallback<List<Article>>() {
                override fun onSuccess(articles: List<Article>) {
                    articles.forEach { article ->
                        article.title.let {
                            val hcArticle = HCArticle(
                                article.id,
                                article.title ?: "",
                                article.body ?: "",
                                article.htmlUrl ?: ""
                            )
                            articleList.add(hcArticle)
                        }
                    }
                    result(articleList)
                }

                override fun onError(errorResponse: ErrorResponse) {
                    Log.d("TAG", "onError: ${errorResponse.reason}")
                    result(articleList)
                }
            }) ?: result(articleList)
    }


}