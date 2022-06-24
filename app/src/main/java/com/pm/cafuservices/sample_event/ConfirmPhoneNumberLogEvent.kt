package com.pm.cafuservices.sample_event

import com.pm.cafuservices.components.analytics.AnalyticsKit
import com.pm.cafuservices.components.analytics.events.CustomEvent

class ConfirmPhoneNumberLogEvent constructor(
    userStatus: Int,
    phoneNumber: String,
    countryCode: String,
) : CustomEvent {

    private val userStatus: String
    private val carrier: String
    private val countryCode: String

    init {
        this.userStatus = returnUserStatusWhileLogging(userStatus)
        this.carrier = returnCarrier(phoneNumber)
        this.countryCode = countryCode
    }


    override fun getEventName(kit: AnalyticsKit) = EventNames.EventName.PHONE_ENTERED

    override fun getParameters(kit: AnalyticsKit): MutableMap<String, Any> {
        val parameters = super.getParameters(kit)

        parameters[EventNames.ParamName.USER_STATUS] = userStatus
        parameters[EventNames.ParamName.CARRIER] = carrier
        parameters[EventNames.ParamName.COUNTRY_CODE] = countryCode

        return parameters
    }

    private fun returnCarrier(phoneNumber: String): String {
        val firstTwoChars =
            if (phoneNumber[0] == '0') phoneNumber.substring(1, 3) else phoneNumber.substring(0, 2)
        firstTwoChars.let {
            return when (it) {
                "50", "54", "56" -> EventNames.ParamValues.ETISALAT
                "52", "55" -> EventNames.ParamValues.DU
                "58" -> EventNames.ParamValues.VIRGIN_MOBILE
                else -> ""
            }
        }
    }

    private fun returnUserStatusWhileLogging(userStatus: Int): String {
        return if (userStatus == 0) EventNames.ParamValues.NEW_USER else EventNames.ParamValues.RETURNING_USER
    }

}