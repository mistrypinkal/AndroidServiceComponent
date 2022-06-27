package com.pm.cafuservices.components.customer_service

import com.pm.cafuservices.components.customer_service.manager.model.CSVisitorInfo

class CustomerService constructor(
    private val chatManager: LiveChatProvider,

    ) {

    init {
        chatManager.init()
        chatManager.setIdentity()
    }

    fun liveChat(csVisitorInfo: CSVisitorInfo){
        chatManager.executeLiveChat(csVisitorInfo)
    }

    fun createTicket(){

    }

    fun getHelpCentreFAQs(){

    }
}