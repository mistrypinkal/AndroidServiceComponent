package com.pm.cafuservices.user_properties.base

/**
 * @Author: Pinkal Mistry
 * @Date: 05/07/2022 1:03 PM
 * @Version: 1.0
 * @Description: TODO
 */
interface ServiceCheckUp {

    fun shouldShow(): Boolean

    fun didReview()
}