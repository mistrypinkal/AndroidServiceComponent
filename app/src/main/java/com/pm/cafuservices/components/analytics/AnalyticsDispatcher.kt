@file:Suppress("UNCHECKED_CAST")

package com.pm.cafuservices.components.analytics

import android.util.Log
import com.pm.cafuservices.components.analytics.events.*
import com.pm.cafuservices.components.analytics.events.base.Event

/**
 * @Author: Pinkal Mistry
 * @Date: 26/06/2022 6:48 PM
 * @Version: 1.0
 * @Description: TODO
 */
interface AnalyticsDispatcher {

    val init: Boolean

    val kit: AnalyticsKit

    val dispatcherName: String


    /**
     * Should call the analytics library's initiation methods
     */
    fun initDispatcher()

    fun trackCustomEvent(event: CustomEvent)

    fun setUserProperties(properties: UserProperties)

    fun setUserProfile(properties: UserProperties)

    fun updateUserProfile(properties: UserProperties)

    fun getUserProperty(event: GetUserProperty): Any?

    /**
     * This method is called from the parent @Analytics class for each event.
     * Override this method if you plan on interfacing your own event types.
     */
    fun track(event: Event) {
        // track the event only if it is not configured as excluded
        if (event.isConsideredIncluded(kit)) {

            var handled = false

            // track for each type differently, including multiple implementations
            when (event) {
                is CustomEvent -> {
                    //trackCustomEvent(event)
                    handled = true
                }
                is SetUserProperty -> {
                    //setUserProperties(event)
                    handled = true
                }
                is SetUserProfile -> {
                    //setUserProfile(event)
                    handled = true
                }
                is UpdateUserProfile -> {
                    //setUserProfile(event)
                    handled = true
                }
            }

        } else {
            Log.d("TAG", "track: Error")
        }
    }

    fun fetch(event: Event): Any? {
        // track the event only if it is not configured as excluded
        if (event.isConsideredIncluded(kit)) {

            if (event is GetUserProperty)
                return getUserProperty(event)
        }

        return null
    }

}