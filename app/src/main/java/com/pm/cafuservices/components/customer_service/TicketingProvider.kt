package com.pm.cafuservices.components.customer_service

import com.pm.cafuservices.components.customer_service.manager.model.CSResult
import com.pm.cafuservices.components.customer_service.manager.model.TicketCustomFields
import java.io.File

interface TicketingProvider : CustomerServiceProvider {


    /**
     * Create Ticket
     */
    fun createTicket(
        subject: String,
        description: String,
        tag: List<String>,
        customFields: List<TicketCustomFields> = emptyList(),
        photoFile: File? = null,
        result: (CSResult<Boolean>) -> Unit
    )

}