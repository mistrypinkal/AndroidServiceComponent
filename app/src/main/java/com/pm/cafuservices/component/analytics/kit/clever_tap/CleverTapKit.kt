package com.pm.cafuservices.component.analytics.kit.clever_tap

import com.pm.cafuservices.component.analytics.AnalyticsKit

class CleverTapKit private constructor() : AnalyticsKit {

    override val name: String = "cleverTap"

    private object Holder {
        val INSTANCE = CleverTapKit()
    }

    companion object {
        val instance: CleverTapKit by lazy { Holder.INSTANCE }
    }


}