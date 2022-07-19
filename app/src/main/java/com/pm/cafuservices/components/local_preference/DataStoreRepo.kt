package com.pm.cafuservices.components.local_preference

import kotlinx.coroutines.flow.Flow

/**
 * @Author: Pinkal
 * @Date: 15/07/2022 4:06 PM
 * @Version: 1.0
 * @Description: TODO
 */
interface DataStoreRepo {

    // Identity ------------------------------------------------------------------------------------

    // Access token
    suspend fun getAccessToken(): String
    suspend fun saveAccessToken(accessToken: String)

    // View Configuration --------------------------------------------------------------------------

    // Promo code visibility
    val promoCodeVisibility: Flow<Boolean>
    suspend fun savePromoCodeVisibility(isVisible: Boolean)
}