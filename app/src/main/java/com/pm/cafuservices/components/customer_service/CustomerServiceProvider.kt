package com.pm.cafuservices.components.customer_service

import com.pm.cafuservices.components.customer_service.manager.model.UserIdentity

/**
 * @Author: Pinkal Mistry
 * @Date: 29/06/2022 10:26 AM
 * @Version: 1.0
 * @Description: TODO
 */
interface CustomerServiceProvider {

    /**
     * Init Customer service provider
     */
    fun init()

    /**
     * Set the identity for the provider
     */
    fun setIdentity(userIdentity: UserIdentity)

    /**
     * Set the visitor info like,
     * User name, mobile number and phone number
     */
    fun <T> getVisitorInfo(userIdentity: UserIdentity): T? {
        return null
    }
}