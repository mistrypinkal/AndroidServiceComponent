package com.pm.cafuservices.component.analytics.new_relic

import com.newrelic.agent.android.NewRelic
import com.pm.cafuservices.component.old.EventOld

class NewRelicEventProcessor {
    fun logEvent(event: EventOld) {
       // NewRelic.recordCustomEvent(event.eventName, event.params)
    }
}