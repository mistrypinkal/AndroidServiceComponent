package com.pm.cafuservices.component.analytics.events

import com.pm.cafuservices.component.analytics.Analytics
import com.pm.cafuservices.component.analytics.AnalyticsKit
import com.pm.cafuservices.component.analytics.events.base.Event


interface CustomEvent: Event {

    fun getEventName(kit: AnalyticsKit) : String

    fun getParameters(kit: AnalyticsKit) : MutableMap<String, Any>{
        return mutableMapOf()
    }

}