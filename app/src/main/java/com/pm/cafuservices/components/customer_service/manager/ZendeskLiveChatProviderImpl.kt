package com.pm.cafuservices.components.customer_service.manager

import android.content.Context
import android.content.Intent
import com.pm.cafuservices.components.customer_service.LiveChatProvider
import com.pm.cafuservices.components.customer_service.manager.model.UserIdentity
import com.zopim.android.sdk.api.ZopimChat
import com.zopim.android.sdk.model.VisitorInfo
import com.zopim.android.sdk.prechat.ZopimChatActivity

/**
 * @Author: Pinkal Mistry
 * @Date: 29/06/2022 10:26 AM
 * @Version: 1.0
 * @Description: TODO
 */
class ZendeskLiveChatProviderImpl(val context: Context) : LiveChatProvider {

    override fun init() {
        ZopimChat.init("zendDeskID")
    }

    override fun setIdentity(userIdentity: UserIdentity) {
    }

    /**
     * Get visitor info in form of Zendesk VisitorInfo class
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T> getVisitorInfo(userIdentity: UserIdentity): T? {
        val visitorName = String.format(
            "%s %s", userIdentity.firstName,
            userIdentity.lastName
        )
        val visitorEmail = userIdentity.email
        val visitorPhone = userIdentity.mobileNumber
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
    override fun executeLiveChat(userIdentity: UserIdentity) {
        ZopimChat.setVisitorInfo(getVisitorInfo(userIdentity))
        context.startActivity(Intent(context, ZopimChatActivity::class.java))
        ZopimChat.trackEvent("Started chat without config")
    }
}