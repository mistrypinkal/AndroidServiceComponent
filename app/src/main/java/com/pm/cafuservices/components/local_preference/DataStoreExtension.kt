package com.pm.cafuservices.components.local_preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

/**
 * @Author: Pinkal Mistry
 * @Date: 19/07/2022 10:11 AM
 * @Version: 1.0
 * @Description: Extension function for DataStore
 */
abstract class DataStoreExtension {

    /**
     * Extension function for set the value in DataStore
     * @param key  Key for values stored in Preferences. Type T is the type of the value associated with the
     * Key.
     *
     * T must be one of the following: Boolean, Int, Long, Float, String, Set<String>.
     *
     * Construct Keys for your data type using: [booleanPreferencesKey], [intPreferencesKey],
     * [longPreferencesKey], [floatPreferencesKey], [stringPreferencesKey], [stringSetPreferencesKey]
     *
     * @param value should be also T type as per the key
     *
     * Example usage: val COUNTER_KEY = intPreferencesKey("my_counter")
     * dataStore.edit { prefs -> prefs\[COUNTER_KEY\] = prefs\[COUNTER_KEY\] :? 0 + 1 }
     */
    suspend fun <T> DataStore<Preferences>.setValue(
        key: Preferences.Key<T>,
        value: T
    ) {
        this.edit {
            it[key] = value
        }
    }

    /**
     * Extension function for get the value from DataStore
     * @param key  Key for values stored in Preferences. Type T is the type of the value associated with the
     * Key.
     *
     * T must be one of the following: Boolean, Int, Long, Float, String, Set<String>.
     *
     * Construct Keys for your data type using: [booleanPreferencesKey], [intPreferencesKey],
     * [longPreferencesKey], [floatPreferencesKey], [stringPreferencesKey], [stringSetPreferencesKey]
     *
     * @param defaultValue should be also T type as per the key
     *
     * Example usage: val COUNTER_KEY = intPreferencesKey("my_counter")
     * dataStore.edit { prefs -> prefs\[COUNTER_KEY\] = prefs\[COUNTER_KEY\] :? 0 + 1 }
     *
     * @return a flow containing the results of applying the given transform function to each value of the original flow.
     */
    fun <T> DataStore<Preferences>.getValueAsFlow(
        key: Preferences.Key<T>,
        defaultValue: T
    ): Flow<T> {
        return this.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { it[key] ?: defaultValue }
    }


}