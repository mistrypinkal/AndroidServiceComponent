package com.pm.cafuservices.component.analytics.events.base

import com.pm.cafuservices.component.analytics.AnalyticsKit

interface Event {

    val excludedKits: List<AnalyticsKit>
        get() = emptyList()

    val includedKits: List<AnalyticsKit>
        get() = emptyList()

    /**
     * @return false if *kit* is considered "excluded". true if considered "included".
     */
    fun isConsideredIncluded(kit: AnalyticsKit): Boolean {

        if (excludedKits.contains(kit)) {
            // not included if declared excluded
            return false
        }

        if (includedKits.isNotEmpty() && includedKits.contains(kit).not()) {
            // not included if includedKits has anything, but doesn't have this kit
            return false
        }

        return true
    }

}