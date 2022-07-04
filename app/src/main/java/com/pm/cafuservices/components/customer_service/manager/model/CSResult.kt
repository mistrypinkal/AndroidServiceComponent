package com.pm.cafuservices.components.customer_service.manager.model

sealed class CSResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : CSResult<T>()
    data class Error(val exception: Exception) : CSResult<Nothing>()
    object InProgress : CSResult<Nothing>()
}