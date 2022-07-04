package com.pm.cafuservices.components.customer_service

import com.pm.cafuservices.components.customer_service.manager.model.TicketCustomFields
import java.io.File

interface TicketingProvider : CustomerServiceProvider {

    fun createTicket(
        subject: String,
        description: String,
        tag: List<String>,
        customFields: List<TicketCustomFields> = emptyList(),
        photoFile: File? = null
    ): Boolean

}