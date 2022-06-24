package com.pm.cafuservices

import androidx.multidex.MultiDexApplication
import com.clevertap.android.sdk.CleverTapAPI
import com.pm.cafuservices.component.analytics.Analytics
import com.pm.cafuservices.component.analytics.AnalyticsSettings
import com.pm.cafuservices.component.analytics.kit.firebase.FirebaseAnalyticsDispatcherImpl
import io.branch.referral.Branch

class BaseApplication : MultiDexApplication() {

    lateinit var analytics: Analytics


    override fun onCreate() {
        super.onCreate()

        // ZopimChat.trackEvent("Application created")

        initAnalytics()

        initNewRelic()

        initCleverTap()

        initBranch()
    }

    private fun initAnalytics() {
        // set an analytics enabled / disabled via SharedPrefs, Database, or anything else
        val settings = AnalyticsSettings().also {
            it.isAnalyticsEnabled = true
        }

        // init analytics property. this is in charge of tracking all events
        analytics = Analytics(
            settings,
            FirebaseAnalyticsDispatcherImpl(init = true, context = this),
            FirebaseAnalyticsDispatcherImpl(init = true, context = this),
        )

    }


    private fun initNewRelic() {
        /*if (BuildConfig.FLAVOR == "production") {
            NewRelic.withApplicationToken(BuildConfig.NEW_RELIC_KEY).start(this)
        }*/
    }

    private fun initCleverTap() {
        val cleverTapAPI = CleverTapAPI.getDefaultInstance(this)
        cleverTapAPI?.enableDeviceNetworkInfoReporting(true)

        /*if (BuildConfig.FLAVOR == "production") {
            CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.OFF)
        } else {
            CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.DEBUG)
        }*/
    }

    private fun initBranch() {
        Branch.getAutoInstance(this)
        Branch.enableLogging()
    }
}