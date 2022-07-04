package com.pm.cafuservices.components.analytics.kit.clever_tap

import android.content.Context
import com.clevertap.android.sdk.CleverTapAPI
import com.pm.cafuservices.components.analytics.AnalyticsDispatcher
import com.pm.cafuservices.components.analytics.AnalyticsKit
import com.pm.cafuservices.components.analytics.events.CustomEvent
import com.pm.cafuservices.components.analytics.events.UserProperties

class CleverTapAnalyticsDispatcherImpl(
    override val init: Boolean,
    private val context: Context
) : AnalyticsDispatcher {

    private var cleverTapAPI: CleverTapAPI? = null

    companion object {
        const val DispatcherName = "DefaultCleverTapDispatcher"
    }

    override val kit: AnalyticsKit = CleverTapKit.instance

    override val dispatcherName: String = DispatcherName

    override fun initDispatcher() {
        cleverTapAPI = CleverTapAPI.getDefaultInstance(context)
    }

    override fun trackCustomEvent(event: CustomEvent) {
        cleverTapAPI?.pushEvent(event.getEventName(kit).firebaseFriendly(), event.getParameters())
    }

    override fun setUserProperties(properties: UserProperties) {
        val userProperty = HashMap<String, Any>(1)
        properties.getUserProperties(kit).forEach {
            userProperty[it.key] = it.value
        }

        cleverTapAPI?.pushProfile(userProperty)
    }

    private fun String.firebaseFriendly(): String {

        val freebased = lowercase().replace(" ", "_")

        if (freebased.length > 30) {
            throw IllegalStateException("CleverTap event key shouldn't have more than 30 chars ($freebased)")
        }

        return freebased

    }

    private fun CustomEvent.getParameters(): HashMap<String, Any?> {
        val hashMap = HashMap<String, Any?>()
        getParameters(kit).forEach {
            when (it.value) {
                is String -> hashMap[it.key] = it.value.toString().lowercase()
                else -> hashMap[it.key] = it.value
            }

        }
        return hashMap
    }
}

