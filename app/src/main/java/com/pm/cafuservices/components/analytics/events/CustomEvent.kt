package com.pm.cafuservices.components.analytics.events

import com.pm.cafuservices.components.analytics.AnalyticsKit
import com.pm.cafuservices.components.analytics.events.base.Event


/**
 * @Author: Pinkal Mistry
 * @Date: 26/06/2022 10:26 PM
 * @Version: 1.0
 * @Description: TODO
 */
interface CustomEvent : Event {

    fun getEventName(kit: AnalyticsKit): String

    fun getParameters(kit: AnalyticsKit): MutableMap<String, Any> {
        return mutableMapOf()
    }

}