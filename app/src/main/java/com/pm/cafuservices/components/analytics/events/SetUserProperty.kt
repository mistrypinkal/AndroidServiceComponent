package com.pm.cafuservices.components.analytics.events

import com.pm.cafuservices.components.analytics.AnalyticsKit

interface SetUserProperty : SetUserProperties {

    val key: String
    val value: String

    override fun getUserProperties(kit: AnalyticsKit): MutableMap<String, Any> {
        val userProperties = super.getUserProperties(kit)
        userProperties[key] = value
        return userProperties
    }
}