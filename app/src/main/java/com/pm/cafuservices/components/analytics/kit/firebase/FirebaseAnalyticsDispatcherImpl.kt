package com.pm.cafuservices.components.analytics.kit.firebase

import android.content.Context
import android.os.Bundle
import com.pm.cafuservices.components.analytics.AnalyticsDispatcher
import com.pm.cafuservices.components.analytics.AnalyticsKit
import com.pm.cafuservices.components.analytics.events.CustomEvent
import com.pm.cafuservices.components.analytics.events.UserProperties

/**
 * @Author: Pinkal Mistry
 * @Date: 04/07/2022 3:13 PM
 * @Version: 1.0
 * @Description: TODO
 */
class FirebaseAnalyticsDispatcherImpl(
    override val init: Boolean,
    private val context: Context
) : AnalyticsDispatcher {

    // var firebaseAnalytics: FirebaseAnalytics? = null

    companion object {
        const val DispatcherName = "DefaultFirebaseDispatcher"
    }

    override val kit: AnalyticsKit = FirebaseKit.instance

    override val dispatcherName: String = DispatcherName

    override fun initDispatcher() {
        //  firebaseAnalytics = FirebaseAnalytics.getInstance(context)
    }

    override fun trackCustomEvent(event: CustomEvent) {
        //  firebaseAnalytics?.logEvent(event.getEventName(kit).firebaseFriendly(), event.getBundle())
    }

    override fun setUserProperties(properties: UserProperties) {
    }


    override fun setUserProfile(properties: UserProperties) {
        //firebase.onUserLogin(properties.getUserProperties())
    }

    override fun updateUserProfile(properties: UserProperties) {
    }


    private fun String.firebaseFriendly(): String {

        val freebased = lowercase().replace(" ", "_")

        if (freebased.length > 40) {
            throw IllegalStateException("firebase event key shouldn't have more than 40 chars ($freebased)")
        }

        return freebased

    }

    private fun CustomEvent.getBundle(): Bundle {
        val bundle = Bundle()

        getParameters(kit).forEach {
            when (it.value) {
                is Int -> bundle.putInt(it.key, it.value as Int)
                is Float -> bundle.putFloat(it.key, it.value as Float)
                is Double -> bundle.putDouble(it.key, it.value as Double)
                is Long -> bundle.putLong(it.key, it.value as Long)
                // other stuff
                is String -> bundle.putString(it.key, it.value as String)
                is Boolean -> bundle.putBoolean(it.key, it.value as Boolean)
                else -> throw RuntimeException("value type " + it.value.javaClass.toString() + " is illegal")
            }
        }

        return bundle
    }

    private fun UserProperties.getUserProperties(): HashMap<String, Any?> {
        val hashMap = HashMap<String, Any?>()

        getUserProperties(kit).forEach {
            hashMap[it.key] = it.value
        }

        return hashMap
    }
}