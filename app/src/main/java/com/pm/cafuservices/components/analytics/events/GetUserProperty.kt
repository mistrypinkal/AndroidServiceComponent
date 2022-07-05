package com.pm.cafuservices.components.analytics.events

import com.pm.cafuservices.components.analytics.AnalyticsKit
import com.pm.cafuservices.components.analytics.events.base.Event

/**
 * @Author: Pinkal Mistry
 * @Date: 05/07/2022 12:41 PM
 * @Version: 1.0
 * @Description: TODO
 */
interface GetUserProperty : Event {

    val key: String

    override val includedKits: List<AnalyticsKit>
}