package com.pm.cafuservices.components.analytics

import com.pm.cafuservices.components.analytics.events.base.Event

/**
 * @Author: Pinkal Mistry
 * @Date: 26/06/2022 6:48 PM
 * @Version: 1.0
 * @Description: TODO
 */
class Analytics constructor(
    private val settings: AnalyticsSettings,
    private vararg val dispatchers: AnalyticsDispatcher
) {

    init {
        // init all dispatchers
        dispatchers.forEach { dispatcher ->
            if (dispatcher.init) {
                dispatcher.initDispatcher()
            }
        }

    }

    /**
     * Track Event  ---------------------------------------------------------------------
     */
    /**
     * Call this to track one or more *Events*
     */
    fun track(vararg events: Event) {

        if (settings.isAnalyticsEnabled.not()) return
        events.forEach { event ->

            dispatchers.forEach { dispatcher ->

                if (settings.enabledKits.isDisabled(dispatcher.kit)) return
                if (settings.enabledDispatchers.isDisabled(dispatcher.dispatcherName)) return

                try {
                    dispatcher.track(event)
                } catch (e: Exception) {
                }
            }
        }
    }


    fun fetch(event: Event): Any? {
        dispatchers.forEach { dispatcher ->
            try {
                return dispatcher.fetch(event)
            } catch (e: Exception) {
            }
        }
        return null
    }


    /**
     * Set Kit as enabled or disabled for future event dispatches
     */
    fun setKitEnabled(kit: AnalyticsKit, enabled: Boolean) {
        settings.enabledKits[kit] = enabled
    }

    /**
     * Set Dispatcher as enabled or disabled for future event dispatches
     */
    fun setDispatcherEnabled(dispatcherName: String, enabled: Boolean) {
        settings.enabledDispatchers[dispatcherName] = enabled
    }

}
