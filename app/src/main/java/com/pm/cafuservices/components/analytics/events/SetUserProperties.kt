package com.pm.cafuservices.components.analytics.events

import com.pm.cafuservices.components.analytics.AnalyticsKit
import com.pm.cafuservices.components.analytics.events.base.Event

interface SetUserProperties : Event {
    fun getUserProperties(kit: AnalyticsKit): MutableMap<String, Any>{
        return mutableMapOf()
    }
}