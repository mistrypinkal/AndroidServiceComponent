package com.pm.cafuservices.components.analytics.kit.branch

import com.pm.cafuservices.components.analytics.AnalyticsKit

/**
 * @Author: Pinkal Mistry
 * @Date: 04/07/2022 3:39 PM
 * @Version: 1.0
 * @Description: TODO
 */
class BranchKit private constructor(): AnalyticsKit {
    override val name: String = "Branch"

    private object Holder {
        val INSTANCE = BranchKit()
    }

    companion object {
        val instance: BranchKit by lazy { Holder.INSTANCE }
    }
}