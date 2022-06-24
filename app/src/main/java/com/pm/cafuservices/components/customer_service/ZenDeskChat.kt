package com.pm.cafuservices.components.customer_service

import android.content.Context


class ZenDeskChat constructor(
    private val context: Context,
    private val firstName: String,
    private val lastName: String,
    private val email: String,
    private val mobileNumber: String
) {
    init {
       // ZopimChat.init("zendDeskID")
    }

    fun launchZenDesk() {
        val visitorName = String.format(
            "%s %s", firstName,
            lastName
        )
        val visitorEmail = email
        val visitorPhone = mobileNumber
      /*  val visitorInfo = VisitorInfo.Builder()
            .name(visitorName)
            .email(visitorEmail)

            .phoneNumber(visitorPhone)
            .build()
        ZopimChat.setVisitorInfo(visitorInfo)
        context.startActivity(Intent(context, ZopimChatActivity::class.java))
        ZopimChat.trackEvent("Started chat without config")*/
    }
}