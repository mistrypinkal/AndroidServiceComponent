package com.pm.cafuservices.components.analytics.kit.firebase

import com.pm.cafuservices.components.analytics.AnalyticsKit

class FirebaseKit private constructor() : AnalyticsKit {

    override val name: String = "firebase"

    private object Holder {
        val INSTANCE = FirebaseKit()
    }

    companion object {
        val instance: FirebaseKit by lazy { Holder.INSTANCE }
    }


}