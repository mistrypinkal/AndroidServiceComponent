package com.pm.cafuservices.components.analytics.kit.clever_tap

import android.content.Context
import com.clevertap.android.sdk.CleverTapAPI
import com.pm.cafuservices.components.analytics.AnalyticsDispatcher
import com.pm.cafuservices.components.analytics.AnalyticsKit
import com.pm.cafuservices.components.analytics.events.CustomEvent
import com.pm.cafuservices.components.analytics.events.UserProperties

/**
 * @Author: Pinkal Mistry
 * @Date: 26/06/2022 6:48 PM
 * @UpdatedDate: 04/07/2022 2:30
 * @Version: 1.0
 * @Description: TODO
 */
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
        cleverTapAPI?.pushProfile(properties.getUserProperties())
    }

    override fun setUserProfile(properties: UserProperties) {
        cleverTapAPI?.onUserLogin(properties.getUserProperties())
    }

    override fun updateUserProfile(properties: UserProperties) {
        cleverTapAPI?.pushProfile(properties.getUserProperties())
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

    private fun UserProperties.getUserProperties(): HashMap<String, Any?> {
        val hashMap = HashMap<String, Any?>()
        getUserProperties(kit).forEach {
            hashMap[it.key] = it.value
        }
        return hashMap
    }
}

