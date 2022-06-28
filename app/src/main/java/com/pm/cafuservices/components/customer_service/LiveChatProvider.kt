package com.pm.cafuservices.components.customer_service

import com.pm.cafuservices.components.customer_service.manager.model.CSVisitorInfo

interface LiveChatProvider : CustomerServiceProvider {

    /**
     * Execute live chat
     */
    fun executeLiveChat(csVisitorInfo: CSVisitorInfo)

    /**
     *  Process the chat with set visitor info and then execute the live chat
     */
    fun execute(csVisitorInfo: CSVisitorInfo){

        // execute the chat
        executeLiveChat(csVisitorInfo)
    }
}