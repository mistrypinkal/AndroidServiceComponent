package com.pm.cafuservices.components.analytics.di

import android.content.Context
import com.pm.cafuservices.components.analytics.Analytics
import com.pm.cafuservices.components.analytics.AnalyticsDispatcher
import com.pm.cafuservices.components.analytics.AnalyticsSettings
import com.pm.cafuservices.components.analytics.kit.clever_tap.CleverTapAnalyticsDispatcherImpl
import com.pm.cafuservices.components.analytics.kit.firebase.FirebaseAnalyticsDispatcherImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

/**
 * @Author: Pinkal Mistry
 * @Date: 05/07/2022 5:26 PM
 * @Version: 1.0
 * @Description: TODO
 */
@Module
@InstallIn(SingletonComponent::class)
object AnalyticsModule {

    @Provides
    @Singleton
    fun provideAnalytics(
        settings: AnalyticsSettings,
        @Named("CleverTap") cleverTapAnalyticsDispatcherImpl: AnalyticsDispatcher,
        @Named("Firebase") firebaseAnalyticsDispatcherImpl: AnalyticsDispatcher
    ): Analytics {
        return Analytics(
            settings,
            cleverTapAnalyticsDispatcherImpl,
            firebaseAnalyticsDispatcherImpl
        )
    }

    @Provides
    @Singleton
    fun provideSetting(): AnalyticsSettings {
        return AnalyticsSettings()
    }

    @Provides
    @Singleton
    @Named("CleverTap")
    fun provideCleverTapAnalyticsDispatcher(
        @ApplicationContext appContext: Context
    ): AnalyticsDispatcher {
        return CleverTapAnalyticsDispatcherImpl(init = true, appContext)
    }

    @Provides
    @Singleton
    @Named("Firebase")
    fun provideFirebaseAnalyticsDispatcher(
        @ApplicationContext appContext: Context
    ): AnalyticsDispatcher {
        return FirebaseAnalyticsDispatcherImpl(init = true, appContext)
    }

}