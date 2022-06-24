package com.pm.cafuservices.component.old

data class EventOld(
    val eventName: String,
    val params: HashMap<String, Any> = HashMap(),
    val transactionId: String? = null
)