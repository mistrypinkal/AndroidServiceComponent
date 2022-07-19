package com.pm.cafuservices.components.local_preference

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

/**
 * @Author: Pinkal Mistry
 * @Date: 15/07/2022 4:08 PM
 * @Version: 1.0
 * @Description: TODO
 */
object DataStoreKeys {
    object IdentityKeys {
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
    }
    object ViewConfigurationKeys {
        val PROMO_CODE_VISIBILITY = booleanPreferencesKey("promo_code_visibility")
    }
}