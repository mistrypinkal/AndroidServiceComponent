package com.pm.cafuservices.components.analytics.events

import com.pm.cafuservices.components.analytics.AnalyticsKit
import com.pm.cafuservices.components.analytics.events.base.Event

/**
 * @Author: Pinkal Mistry
 * @Date: 26/06/2022 6:48 PM
 * @Version: 1.0
 * @Description: TODO
 */
interface UserProperties : Event {
    fun getUserProperties(kit: AnalyticsKit): MutableMap<String, Any> {
        return mutableMapOf()
    }
}