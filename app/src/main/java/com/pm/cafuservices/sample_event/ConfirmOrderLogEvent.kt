package com.pm.cafuservices.sample_event

import com.pm.cafuservices.components.analytics.AnalyticsKit
import com.pm.cafuservices.components.analytics.events.CustomEvent
import com.pm.cafuservices.components.analytics.kit.branch.BranchKit
import com.pm.cafuservices.components.analytics.kit.clever_tap.CleverTapKit
import com.pm.cafuservices.components.analytics.kit.firebase.FirebaseKit


/**
 * @Author: Pinkal
 * @Date: 04/07/2022 3:51 PM
 * @Version: 1.0
 * @Description: TODO
 */
class ConfirmOrderLogEvent(
    val orderId: String,
    val service: String,
    val orderCurrency: String,
    val orderPrice: String,
    val cardName: String,
    val cardType: String
) : CustomEvent {

    // include kit which is required to log
    override val includedKits: List<AnalyticsKit>
        get() = listOf(CleverTapKit.instance, BranchKit.instance, FirebaseKit.instance)

    /**
     * Event based on the kit
     */
    override fun getEventName(kit: AnalyticsKit): String = when (kit) {
        is CleverTapKit -> "ConfirmOrder"
        is BranchKit -> "Purchase"
        is FirebaseKit -> "confirm_order"
        else -> ""
    }

    /**
     * Event params key and values on the kit
     */
    override fun getParameters(kit: AnalyticsKit): MutableMap<String, Any> {
        val parameter = super.getParameters(kit)
        when (kit) {
            is CleverTapKit -> {
                parameter["orderId"] = orderId
                parameter["service"] = service
                parameter["orderCurrency"] = orderCurrency
                parameter["orderPrice"] = orderPrice
                parameter["cardName"] = cardName
                parameter["cardType"] = cardType
            }
            is BranchKit -> {
                parameter["Product"] = service
                parameter["Currency"] = orderCurrency
                parameter["Price"] = orderPrice.toDouble()
                parameter["BankName"] = cardName
            }
            is FirebaseKit -> {
                parameter["order_id"] = orderId
                parameter["service"] = service
                parameter["order_currency"] = orderCurrency
                parameter["order_price"] = orderPrice
                parameter["card_name"] = cardName
                parameter["card_type"] = cardType
            }
            else -> {
            }
        }

        // add common param
        parameter["description"] = "Description"

        return parameter
    }

    override fun getTransactionId(kit: AnalyticsKit) = orderId
}