package com.pm.cafuservices.components.old

interface BaseEvent {

    // Event name
    fun getEventName(): String

    // Event HasMap param
    fun getParamHashMap(): HashMap<String, Any>

    // Event mapper
    fun mapParamToEvent(): EventOld

    // Event log to different platform
    fun eventLogTo(): List<EventLogTo>
}

enum class EventLogTo {
    CLEVER_TAP,
    FIREBASE,
    NEW_RELIC,
    BRANCH
}