package com.pm.cafuservices.components.customer_service

import com.pm.cafuservices.components.customer_service.manager.model.CSResult
import com.pm.cafuservices.components.customer_service.manager.model.TicketCustomFields
import java.io.File

/**
 * @Author: Pinkal Mistry
 * @Date: 01/07/2022 4:44 PM
 * @UpdatedDate: 07/07/2022 5:27 PM
 * @Version: 1.0
 * @Description: TODO
 */
interface TicketingProvider : CustomerServiceProvider {

    /**
     * Create Ticket
     *
     * @param subject - title of the ticket
     * @param description - detail information about the ticket
     * @param tag
     * @param customFields - custom field to pass id tag woth values
     * @param photoFile - multiple photo can be accept and upload
     *
     * @return result boolean as lambda function
     */
    fun createTicket(
        subject: String,
        description: String,
        tag: List<String>,
        customFields: List<TicketCustomFields> = emptyList(),
        photoFile: List<File>? = null,
        result: (CSResult<Boolean>) -> Unit
    )

}