package com.pm.cafuservices.components.analytics

import javax.inject.Inject

/**
 * @Author: Pinkal Mistry
 * @Date: 26/06/2022 6:48 PM
 * @Version: 1.0
 * @Description: TODO
 */
class AnalyticsSettings @Inject constructor(
) {
    val isAnalyticsEnabled: Boolean =true

    val enabledKits: ServiceEnabledMap<AnalyticsKit> = ServiceEnabledMap()
    val enabledDispatchers: ServiceEnabledMap<String> = ServiceEnabledMap()

    class ServiceEnabledMap<Key> : LinkedHashMap<Key, Boolean>() {

        fun isDisabled(key: Key): Boolean = this[key] == false

    }
}