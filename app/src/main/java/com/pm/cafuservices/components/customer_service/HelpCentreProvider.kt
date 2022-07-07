package com.pm.cafuservices.components.customer_service

import com.pm.cafuservices.components.customer_service.manager.model.HCArticle
import com.pm.cafuservices.components.customer_service.manager.model.HCSection
import com.pm.cafuservices.components.customer_service.manager.model.HCSectionWithArticle
import com.pm.cafuservices.components.customer_service.manager.model.CSResult

/**
 * @Author: Pinkal Mistry
 * @Date: 28/06/2022 7:16 PM
 * @Version: 1.0
 * @Description: TODO
 */
interface HelpCentreProvider : CustomerServiceProvider {

    /**
     * Get sections
     */
    fun getSection(result: (CSResult<List<HCSection>>) -> Unit)

    /**
     * Get articles
     */
    fun getArticle(id: Long, result: (CSResult<List<HCArticle>>) -> Unit)

    /**
     * Get sections with articles
     */
    fun getSectionWithArticle(result: (CSResult<List<HCSectionWithArticle>>) -> Unit)

}