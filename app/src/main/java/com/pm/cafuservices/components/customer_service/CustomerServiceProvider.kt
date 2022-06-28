package com.pm.cafuservices.components.customer_service

import com.pm.cafuservices.components.customer_service.manager.model.CSVisitorInfo

interface CustomerServiceProvider {

    /**
     * Init Customer service provider
     */
    fun init()

    /**
     * Set the identity for the provider
     */
    fun setIdentity()

    /**
     * Set the visitor info like,
     * User name, mobile number and phone number
     */
    fun <T> getVisitorInfo(csVisitorInfo: CSVisitorInfo) : T
}