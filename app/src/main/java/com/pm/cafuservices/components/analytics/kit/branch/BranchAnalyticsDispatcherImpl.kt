package com.pm.cafuservices.components.analytics.kit.branch

import android.content.Context
import com.pm.cafuservices.components.analytics.AnalyticsDispatcher
import com.pm.cafuservices.components.analytics.AnalyticsKit
import com.pm.cafuservices.components.analytics.events.CustomEvent
import com.pm.cafuservices.components.analytics.events.GetUserProperty
import com.pm.cafuservices.components.analytics.events.UserProperties
import com.pm.cafuservices.components.analytics.kit.clever_tap.CleverTapKit
import io.branch.referral.util.BranchEvent

/**
 * @Author: Pinkal Mistry
 * @Date: 04/07/2022 3:36 PM
 * @Version: 1.0
 * @Description: TODO
 */
class BranchAnalyticsDispatcherImpl(
    override val init: Boolean,
    val context: Context
) : AnalyticsDispatcher {

    companion object {
        const val DispatcherName = "BranchDispatcher"
    }

    override val kit: AnalyticsKit = CleverTapKit.instance

    override val dispatcherName: String = DispatcherName

    override fun initDispatcher() {
        TODO("Not yet implemented")
    }

    override fun trackCustomEvent(event: CustomEvent) {
        val branchEvent = BranchEvent(event.getEventName(kit))

        event.getTransactionId(kit).let {
            branchEvent.setTransactionID(it)
        }

        event.getParameters(kit).forEach {
            branchEvent.addCustomDataProperty(it.key, it.value.toString())
        }

        branchEvent.logEvent(context)
    }

    override fun setUserProperties(properties: UserProperties) {
        TODO("Not yet implemented")
    }

    override fun setUserProfileInfo(properties: UserProperties) {
        TODO("Not yet implemented")
    }

    override fun updateUserProfile(properties: UserProperties) {
        TODO("Not yet implemented")
    }

    override fun getUserProperty(event: GetUserProperty): Any? {
        return null
    }
}