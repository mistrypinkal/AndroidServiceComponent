package com.pm.cafuservices.components.analytics

import android.util.Log
import com.pm.cafuservices.components.analytics.events.CustomEvent
import com.pm.cafuservices.components.analytics.events.SetUserProperties
import com.pm.cafuservices.components.analytics.events.base.Event

interface AnalyticsDispatcher {

    val init: Boolean

    val kit: AnalyticsKit

    val dispatcherName: String


    /**
     * Should call the analytics library's initiation methods
     */
    fun initDispatcher()

    fun trackCustomEvent(event: CustomEvent)

    fun setUserProperties(properties: SetUserProperties)

    /**
     * This method is called from the parent @Analytics class for each event.
     * Override this method if you plan on interfacing your own event types.
     */
    fun track(event: Event) {
        // track the event only if it is not configured as excluded
        if (event.isConsideredIncluded(kit)) {

            var handled = false

            // track for each type differently, including multiple implementations
            if (event is CustomEvent) {
                //trackCustomEvent(event)
                handled = true
            }

            if (event is SetUserProperties) {
                //setUserProperties(event)
                handled = true
            }


        }else{
            Log.d("TAG", "track: Error")
        }
    }



}