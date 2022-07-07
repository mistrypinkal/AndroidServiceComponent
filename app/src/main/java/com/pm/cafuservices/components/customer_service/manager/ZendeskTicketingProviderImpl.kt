package com.pm.cafuservices.components.customer_service.manager

import android.content.Context
import android.util.Log
import com.pm.cafuservices.components.customer_service.TicketingProvider
import com.pm.cafuservices.components.customer_service.manager.model.CSResult
import com.pm.cafuservices.components.customer_service.manager.model.TicketCustomFields
import com.pm.cafuservices.components.customer_service.manager.model.UserIdentity
import com.zendesk.service.ErrorResponse
import com.zendesk.service.ZendeskCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import zendesk.core.AnonymousIdentity
import zendesk.core.Zendesk
import zendesk.support.*
import java.io.File
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * @Author: Pinkal Mistry
 * @Date: 07/07/2022 3:02 PM
 * @Version: 1.0
 * @Description: TODO
 */
class ZendeskTicketingProviderImpl(
    val context: Context
) : TicketingProvider {

    init {
        init()
    }

    private var requestProvider: RequestProvider? = null
    private var uploadProvider: UploadProvider? = null
    private val job = SupervisorJob()
    private val ioScope by lazy { CoroutineScope(job + Dispatchers.IO) }

    /**
     *  Init Zendesk
     */
    override fun init() {
        Zendesk.INSTANCE.init(
            context, ZendeskHelpCenterProviderImpl.HC_ZENDESK_URL,
            ZendeskHelpCenterProviderImpl.HC_ZENDESK_APP_ID,
            ZendeskHelpCenterProviderImpl.HC_ZENDESK_AUTH_CLIENT_ID
        )
    }

    /**
     * Set identity - Set user detail and create identity
     */
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
    }

    override fun createTicket(
        subject: String,
        description: String,
        tag: List<String>,
        customFields: List<TicketCustomFields>,
        photoFile: List<File>?,
        result: (CSResult<Boolean>) -> Unit
    ) {

        // create request obj for ticket
        val request = CreateRequest()
        request.subject = subject
        request.description = description
        request.tags = listOf("cafu_pilot")
        if (customFields.isNotEmpty())
            request.customFields = customFields.map { CustomField(it.id, it.value) }

        ioScope.launch {
            try {
                photoFile?.let {
                    // upload all image and map to token
                    val tokenList: MutableList<String?> = it.map { photoFile ->
                        val token: String? = try {
                            uploadImageAndGetToken(photoFile)
                        } catch (exception: Exception) {
                            null
                        }
                        token
                    } as MutableList<String?>

                    // add attachment
                    request.setAttachments(tokenList.filterNotNull())
                }

                // create ticket and pass result
                result(CSResult.Success(createTicketFromZendesk(request)))
            } catch (exception: Exception) {
                // Handles exceptions here.
                Log.d("TAG", "createTicket: $exception")
                result(CSResult.Error(exception))
            }
        }

    }

    /**
     * Create ticket on zendesk that
     * accept request and
     * return success as boolean or exception as result
     */
    private suspend fun createTicketFromZendesk(request: CreateRequest): Boolean =
        suspendCoroutine { continuation ->
            // Set provider
            requestProvider = Support.INSTANCE.provider()?.requestProvider()

            requestProvider?.createRequest(request, object : ZendeskCallback<Request>() {
                override fun onSuccess(r: Request?) {
                    continuation.resume(true)
                }

                override fun onError(er: ErrorResponse?) {
                    continuation.resumeWithException(
                        Exception(
                            er?.reason
                                ?: "Unknown error occur while creating ticket. Please try again"
                        )
                    )
                }

            }) ?: continuation.resumeWithException(Exception("Please init request provider"))
        }

    /**
     * Upload image that
     * accept file and file name from file and
     * return success as token or exception as result
     */
    private suspend fun uploadImageAndGetToken(photoFile: File): String? =
        suspendCoroutine { continuation ->
            uploadProvider = Support.INSTANCE.provider()?.uploadProvider()

            uploadProvider?.uploadAttachment(
                photoFile.name,
                photoFile,
                "image/png",
                object : ZendeskCallback<UploadResponse>() {
                    override fun onSuccess(uploadResponse: UploadResponse?) {
                        continuation.resume(uploadResponse?.token)
                    }

                    override fun onError(er: ErrorResponse?) {
                        continuation.resumeWithException(
                            Exception(
                                er?.reason ?: "Error occurred while uploading image"
                            )
                        )
                    }

                }
            ) ?: continuation.resumeWithException(Exception("Please init upload provider"))
        }
}