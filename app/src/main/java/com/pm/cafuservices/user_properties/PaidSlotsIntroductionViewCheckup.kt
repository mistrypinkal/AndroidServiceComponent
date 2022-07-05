package com.pm.cafuservices.user_properties

import com.pm.cafuservices.components.analytics.Analytics
import com.pm.cafuservices.components.analytics.AnalyticsKit
import com.pm.cafuservices.components.analytics.events.GetUserProperty
import com.pm.cafuservices.components.analytics.events.SetUserProperty
import com.pm.cafuservices.components.analytics.kit.clever_tap.CleverTapKit
import com.pm.cafuservices.user_properties.base.ServiceCheckUp

/**
 * @Author: Pinkal Mistry
 * @Date: 05/07/2022 1:04 PM
 * @Version: 1.0
 * @Description: TODO
 */
class PaidSlotsIntroductionViewCheckup(
    val analytics: Analytics
) : ServiceCheckUp {

    val analyticsKit = CleverTapKit.instance

    companion object {
        const val KEY = "paid_slot_view"
    }

    override fun shouldShow(): Boolean {
        val value = analytics.fetch(GetPaidSlotProperties(KEY, analyticsKit))
        if (value is Boolean) return !value

        return true
    }

    override fun didReview() {
        analytics.track(
            SetPaidSlotProperties(
                key = KEY,
                value = true,
                includedKits = listOf(analyticsKit)
            )
        )
    }

    class GetPaidSlotProperties(
        override val key: String,
        override val includedKit: AnalyticsKit
    ) : GetUserProperty


    class SetPaidSlotProperties(
        override val key: String,
        override val value: Any,
        override val includedKits: List<AnalyticsKit>
    ) : SetUserProperty
}