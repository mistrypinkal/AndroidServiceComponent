package com.pm.cafuservices.components.analytics.events

import com.pm.cafuservices.components.analytics.AnalyticsKit

/**
 * @Author: Pinkal Mistry
 * @Date: 04/07/2022 2:24 PM
 * @Version: 1.0
 * @Description: TODO
 */
interface UpdateUserProfile : UserProperties {
    fun getProperties(kit: AnalyticsKit): MutableMap<String, Any>

    override fun getUserProperties(kit: AnalyticsKit): MutableMap<String, Any> {
        val userProperties = super.getUserProperties(kit)
        getProperties(kit).forEach {
            userProperties[it.key] = it.value
        }
        return userProperties
    }
}