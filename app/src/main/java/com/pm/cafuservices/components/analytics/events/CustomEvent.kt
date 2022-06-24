package com.pm.cafuservices.components.analytics.events

import com.pm.cafuservices.components.analytics.AnalyticsKit
import com.pm.cafuservices.components.analytics.events.base.Event


interface CustomEvent: Event {

    fun getEventName(kit: AnalyticsKit) : String

    fun getParameters(kit: AnalyticsKit) : MutableMap<String, Any>{
        return mutableMapOf()
    }

}