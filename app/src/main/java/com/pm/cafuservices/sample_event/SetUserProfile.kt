package com.pm.cafuservices.sample_event

import android.content.res.Resources
import android.os.Build
import com.pm.cafuservices.components.analytics.AnalyticsKit
import com.pm.cafuservices.components.analytics.events.SetUserProfile
import com.pm.cafuservices.components.analytics.kit.clever_tap.CleverTapKit
import java.util.*

/**
 * @Author: Pinkal
 * @Date: 04/07/2022 3:52 PM
 * @Version: 1.0
 *
 * @Description: Set the user profile information and do user login profile to update events
 */
class SetUserProfile(
    private val userId: String,
    private val mobileNumber: String,
    private val name: String? = null,
    private val email: String? = null,
    private val photoURL: String? = null,
    private val customerType: String? = null,
) : SetUserProfile {

    // include only cleverTap for set profile
    override val includedKits: List<AnalyticsKit>
        get() = listOf(CleverTapKit.instance)

    private val deviceLanguage: String
        get() {
            val locale: Locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Resources.getSystem().configuration.locales[0]
            } else {
                Resources.getSystem().configuration.locale
            }
            return locale.displayLanguage
        }

    override fun getProperties(kit: AnalyticsKit): MutableMap<String, Any> {
        val parameters = super.getUserProperties(kit)

        parameters["Identity"] = userId
        parameters["Phone"] = mobileNumber
        name?.let { parameters["Name"] = name }
        email?.let { parameters["Email"] = email }
        photoURL?.let { parameters["Photo"] = photoURL }
        customerType?.let { parameters["customer_type"] = customerType }
        deviceLanguage.let { parameters["device_language"] = deviceLanguage }

        return parameters
    }

}