package com.pm.cafuservices.components.customer_service

import com.pm.cafuservices.components.customer_service.manager.model.HCArticle
import com.pm.cafuservices.components.customer_service.manager.model.HCSection
import com.pm.cafuservices.components.customer_service.manager.model.HCSectionWithArticle
import com.pm.cafuservices.components.customer_service.manager.model.Result

interface HelpCentreProvider : CustomerServiceProvider {

    /**
     * Get section with article
     */
    fun getSection(result: (Result<List<HCSection>>) -> Unit)

    /**
     * Get section with article
     */
    fun getArticle(id: Long, result: (Result<List<HCArticle>>) -> Unit)

    /**
     * Get section with article
     */
    fun getSectionWithArticle(result: (Result<List<HCSectionWithArticle>>) -> Unit)

}