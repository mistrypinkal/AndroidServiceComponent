package com.pm.cafuservices.components.analytics.events

import com.pm.cafuservices.components.analytics.AnalyticsKit
import com.pm.cafuservices.components.analytics.events.base.Event


/**
 * @Author: Pinkal Mistry
 * @Date: 26/06/2022 6:48 PM
 * @UpdatedDate: 04/07/2022 3:45 PM
 * @Version: 1.0
 * @Description: TODO
 */
interface CustomEvent : Event {

    fun getEventName(kit: AnalyticsKit): String

    fun getParameters(kit: AnalyticsKit): MutableMap<String, Any> {
        return mutableMapOf()
    }

    fun getTransactionId(kit: AnalyticsKit): String? = null
}