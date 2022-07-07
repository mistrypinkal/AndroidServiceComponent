package com.pm.cafuservices.components.customer_service

import com.pm.cafuservices.components.customer_service.manager.model.UserIdentity


/**
 * @Author: Pinkal Mistry
 * @Date: 29/06/2022 10:26 AM
 * @Version: 1.0
 * @Description: TODO
 */
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