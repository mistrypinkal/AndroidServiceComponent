package com.pm.cafuservices.component.analytics.kit.branch

import android.content.Context
import com.pm.cafuservices.component.old.EventOld
import io.branch.referral.util.BranchEvent

class BranchEventProcessor(private val context: Context) {
    fun logEvent(event: EventOld) {
        val branchEvent = BranchEvent(event.eventName)
        event.transactionId.let {
            branchEvent.setTransactionID(it)
        }
        for ((key, value) in event.params) {
            branchEvent.addCustomDataProperty(key, value.toString())
        }
        branchEvent.logEvent(context)
    }
}