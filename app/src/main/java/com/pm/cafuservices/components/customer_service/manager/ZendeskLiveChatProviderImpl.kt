package com.pm.cafuservices.components.customer_service.manager

import android.content.Context
import android.content.Intent
import com.pm.cafuservices.components.customer_service.LiveChatProvider
import com.pm.cafuservices.components.customer_service.manager.model.CSVisitorInfo
import com.zopim.android.sdk.api.ZopimChat
import com.zopim.android.sdk.model.VisitorInfo
import com.zopim.android.sdk.prechat.ZopimChatActivity

class ZendeskLiveChatProviderImpl(val context: Context) : LiveChatProvider {

    override fun init() {
        ZopimChat.init("zendDeskID")
    }

    override fun setIdentity() {
    }

    /**
     * Get visitor info in form of Zendesk VisitorInfo class
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T> getVisitorInfo(csVisitorInfo: CSVisitorInfo): T {
        val visitorName = String.format(
            "%s %s", csVisitorInfo.firstName,
            csVisitorInfo.lastName
        )
        val visitorEmail = csVisitorInfo.email
        val visitorPhone = csVisitorInfo.mobileNumber
        return VisitorInfo.Builder()
            .name(visitorName)
            .email(visitorEmail)
            .phoneNumber(visitorPhone)
            .build() as T

    }

    /**
     * Add visitor info
     * Open live chat option
     */
    override fun executeLiveChat(csVisitorInfo: CSVisitorInfo) {
        ZopimChat.setVisitorInfo(getVisitorInfo(csVisitorInfo))
        context.startActivity(Intent(context, ZopimChatActivity::class.java))
        ZopimChat.trackEvent("Started chat without config")
    }
}