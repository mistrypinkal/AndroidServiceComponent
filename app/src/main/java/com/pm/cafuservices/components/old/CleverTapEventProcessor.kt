package com.pm.cafuservices.components.old

import com.clevertap.android.sdk.CleverTapAPI
import com.pm.cafuservices.sample_event.EventNames
import java.util.*

class CleverTapEventProcessor constructor(
    private val cleverTapAPI: CleverTapAPI
) {

    init {
        // cleverTapAPI = CleverTapAPI.getDefaultInstance(context)!!
    }

    fun logEvent(event: EventOld) {
        cleverTapAPI.pushEvent(event.eventName, getEventParams(event))
    }

    /**
     * Profile push --------------------------------------------------------------------------------
     */
    fun onUserLogin(profileUpdate: HashMap<String?, Any?>?) {
        cleverTapAPI.onUserLogin(profileUpdate)
        cleverTapAPI.location = cleverTapAPI.location
        logEnableDeviceNetworkInfoReportingToCleverTap()
    }

    fun pushProfile(profileUpdate: HashMap<String?, Any?>?) {
        cleverTapAPI.pushProfile(profileUpdate)
        cleverTapAPI.location = cleverTapAPI.location
        logEnableDeviceNetworkInfoReportingToCleverTap()
    }

    /**
     * Custom properties ---------------------------------------------------------------------------
     * Set and Get
     */
    fun logSignupDate() {
        logCleverTapCustomUserProperty(EventNames.Properties.SIGNUP_DATE, Date())
    }

    val reviewCarwashOverview: Any
        get() = getCleverTapProperties(EventNames.Properties.REVIEW_CARWASH_TERMS)


    fun logReviewCarwashOverview() {
        logCleverTapCustomUserProperty(EventNames.Properties.REVIEW_CARWASH_TERMS, true)
    }


    /**
     * get event properties in hashMap form
     */
    private fun getEventParams(event: EventOld): HashMap<String, Any?> {
        val hashMap = HashMap<String, Any?>()
        for ((key, value) in event.params) {
            value.let {
                when (value) {
                    is String -> hashMap[key] = value.toString().toLowerCase()
                    else -> hashMap[key] = value
                }

            }

        }
        return hashMap
    }

    // Enable device network reporting
    private fun logEnableDeviceNetworkInfoReportingToCleverTap() {
        cleverTapAPI.enableDeviceNetworkInfoReporting(true)
    }

    // Push user profile or any custom property also
    private fun logCleverTapCustomUserProperty(propertyName: String, propertyValue: Any) {
        val userProperty = HashMap<String, Any>(1)
        userProperty[propertyName] = propertyValue
        cleverTapAPI.pushProfile(userProperty)
    }

    // get custom property
    fun getCleverTapProperties(PropertyName: String?): Any {
        return cleverTapAPI.getProperty(PropertyName)
    }


}