package com.pm.cafuservices.components.local_preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.first
import javax.inject.Inject

/**
 * @Author: Pinkal Mistry
 * @Date: 15/07/2022 3:46 PM
 * @Version: 1.0
 * @Description: TODO
 */
class DataStoreRepoImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DataStoreRepo, DataStoreExtension() {

    private val TAG: String = "DataStoreRepo"


    /**
     * ---------------------------------------------------------------------------------------------
     * Identity preference
     */

    override suspend fun getAccessToken() = dataStore.getValueAsFlow(
        DataStoreKeys.IdentityKeys.ACCESS_TOKEN,
        ""
    ).first()

    override suspend fun saveAccessToken(accessToken: String) {
        dataStore.setValue(DataStoreKeys.IdentityKeys.ACCESS_TOKEN, accessToken)
    }


    /**
     * ---------------------------------------------------------------------------------------------
     * View Configuration preference
     */

    override val promoCodeVisibility = dataStore.getValueAsFlow(
        DataStoreKeys.ViewConfigurationKeys.PROMO_CODE_VISIBILITY,
        false
    )

    override suspend fun savePromoCodeVisibility(isVisible: Boolean) {
        dataStore.setValue(DataStoreKeys.ViewConfigurationKeys.PROMO_CODE_VISIBILITY, isVisible)
    }


}
