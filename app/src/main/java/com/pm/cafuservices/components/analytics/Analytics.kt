package com.pm.cafuservices.components.analytics

import com.pm.cafuservices.components.analytics.events.base.Event

class Analytics constructor(
    val settings: AnalyticsSettings,
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



    /**
     * Profile  ---------------------------------------------------------------------
     * Set user profile to cleverTap
     */
    fun setUserProfile(
        firstName: String?, lastName: String?, email: String?,
        countryCode: String?, phoneNo: String, picUrl: String?,
        vendorId: Int?, customerType: String?
    ) {

    }

    /**
     * Update user profile to cleverTap
     */
    fun updateUserProfile(
        firstName: String, lastName: String, email: String,
        picUrl: String, customerType: String
    ) {

    }
}
