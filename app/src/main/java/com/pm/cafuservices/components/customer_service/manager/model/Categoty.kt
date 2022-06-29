package com.pm.cafuservices.components.customer_service.manager.model

import androidx.annotation.StringRes
import com.pm.cafuservices.R


enum class Category(
    val id: Long,
    @StringRes val titleResId: Int
) {
    MY_ORDERS(
        360002602179L,
        R.string.my_orders
    ),
    FAQ(
        360002473200L,
        R.string.faq
    );

    companion object {
        operator fun get(id: Int): Category? {
            var status: Category? = null
            for (value in values()) {
                if (value.id == id.toLong()) {
                    status = value
                    break
                }
            }
            return status
        }

        const val HC_FAQ_CATEGORY = 360002473200L
        const val HC_MY_ORDERS_CATEGORY = 360002602179L
    }
}