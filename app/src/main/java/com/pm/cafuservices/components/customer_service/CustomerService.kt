package com.pm.cafuservices.components.customer_service

import com.pm.cafuservices.components.customer_service.manager.model.UserIdentity

class CustomerService constructor(
    private val chatManager: LiveChatProvider,
    private val helpCentreProvider: HelpCentreProvider
    ) {

    init {
        chatManager.init()

    }

    fun liveChat(userIdentity: UserIdentity){
        chatManager.executeLiveChat(userIdentity)
    }

    fun createTicket(){

    }

    fun getHelpCentreFAQs(userIdentity: UserIdentity){
        helpCentreProvider.setIdentity(userIdentity)
        helpCentreProvider.getSection {

        }
    }
}