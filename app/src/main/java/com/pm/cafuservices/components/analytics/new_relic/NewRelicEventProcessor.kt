package com.pm.cafuservices.components.analytics.new_relic

import com.pm.cafuservices.components.old.EventOld

class NewRelicEventProcessor {
    fun logEvent(event: EventOld) {
       // NewRelic.recordCustomEvent(event.eventName, event.params)
    }
}