package com.pm.cafuservices.components.customer_service

import com.pm.cafuservices.components.customer_service.manager.model.HCArticle
import com.pm.cafuservices.components.customer_service.manager.model.HCSection
import com.pm.cafuservices.components.customer_service.manager.model.HCSectionWithArticle

interface HelpCentreProvider : CustomerServiceProvider {

    /**
     * Get section with article
     */
    fun getSection(result: (List<HCSection>) -> Unit)

    /**
     * Get section with article
     */
    fun getArticle(id: Long, result: (List<HCArticle>) -> Unit)

    /**
     * Get section with article
     */
    fun getSectionWithArticle(): List<HCSectionWithArticle>

}