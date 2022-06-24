package com.pm.cafuservices.components.old

class AnalyticsOld constructor(
    private val cleverTapEventProcessor: CleverTapEventProcessor
) {

    /**
     * Track Event  ---------------------------------------------------------------------
     */
    fun trackEvent(event: BaseEvent) {
        event.eventLogTo().forEach { eventLogTo ->
            when (eventLogTo) {
                EventLogTo.CLEVER_TAP -> {
                    logCleverTapEvent(event.mapParamToEvent())
                }
                EventLogTo.FIREBASE -> {
                    // Log to firebase
                }
                EventLogTo.NEW_RELIC -> {
                    // Log to new relic
                }
                EventLogTo.BRANCH -> {
                    // Log to Branch
                }
            }
        }
    }

    /**
     * Log event to cleverTap
     */
    private fun logCleverTapEvent(event: EventOld) {
        cleverTapEventProcessor.logEvent(event)
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

    /**
     * Custom UserProperties ---------------------------------------------------------
     */
    fun logSignupDate() {
        cleverTapEventProcessor.logSignupDate()
    }

    /**
     * Get Boolean flag from CleverTap
     */
    val reviewCarwashOverview: Any
        get() = cleverTapEventProcessor.reviewCarwashOverview
}
