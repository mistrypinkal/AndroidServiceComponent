package com.pm.cafuservices.components.customer_service

import com.pm.cafuservices.components.customer_service.manager.model.UserIdentity

interface LiveChatProvider : CustomerServiceProvider {

    /**
     * Execute live chat
     */
    fun executeLiveChat(userIdentity: UserIdentity)

    /**
     *  Process the chat with set visitor info and then execute the live chat
     */
    fun execute(userIdentity: UserIdentity){

        // execute the chat
        executeLiveChat(userIdentity)
    }
}