package com.pm.cafuservices.component.analytics.events

import com.pm.cafuservices.component.analytics.AnalyticsKit
import com.pm.cafuservices.component.analytics.events.base.Event

interface SetUserProperties : Event {
    fun getUserProperties(kit: AnalyticsKit): MutableMap<String, Any>{
        return mutableMapOf()
    }
}