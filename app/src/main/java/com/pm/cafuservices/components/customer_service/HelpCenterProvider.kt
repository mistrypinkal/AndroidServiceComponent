package com.pm.cafuservices.components.customer_service

import com.pm.cafuservices.components.customer_service.manager.model.HCArticle
import com.pm.cafuservices.components.customer_service.manager.model.HCSection
import com.pm.cafuservices.components.customer_service.manager.model.HCSectionWithArticle

interface HelpCenterProvider : CustomerServiceProvider {

    /**
     * Get section with article
     */
    fun getSection(): List<HCSection>

    /**
     * Get section with article
     */
    fun getArticle(id: Long): List<HCArticle>

    /**
     * Get section with article
     */
    fun getSectionWithArticle(): List<HCSectionWithArticle>

}