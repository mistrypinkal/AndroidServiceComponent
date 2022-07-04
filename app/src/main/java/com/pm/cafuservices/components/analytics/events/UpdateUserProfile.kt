package com.pm.cafuservices.components.analytics.events

import com.pm.cafuservices.components.analytics.AnalyticsKit

/**
 * @Author: Pinkal Mistry
 * @Date: 04/07/2022 2:24 PM
 * @Version: 1.0
 * @Description: TODO
 */
interface UpdateUserProfile : UserProperties {
    val key: String
    val value: String

    override fun getUserProperties(kit: AnalyticsKit): MutableMap<String, Any> {
        val userProperties = super.getUserProperties(kit)
        userProperties[key] = value
        return userProperties
    }
}