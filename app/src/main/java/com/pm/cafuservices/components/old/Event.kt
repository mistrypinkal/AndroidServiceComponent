package com.pm.cafuservices.components.old

data class EventOld(
    val eventName: String,
    val params: HashMap<String, Any> = HashMap(),
    val transactionId: String? = null
)