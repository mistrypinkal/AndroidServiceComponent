package com.pm.cafuservices.components.customer_service.di

import android.content.Context
import com.pm.cafuservices.components.customer_service.HelpCentreProvider
import com.pm.cafuservices.components.customer_service.LiveChatProvider
import com.pm.cafuservices.components.customer_service.TicketingProvider
import com.pm.cafuservices.components.customer_service.manager.ZendeskHelpCenterProviderImpl
import com.pm.cafuservices.components.customer_service.manager.ZendeskLiveChatProviderImpl
import com.pm.cafuservices.components.customer_service.manager.ZendeskTicketingProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @Author: Pinkal
 * @Date: 07/07/2022 5:56 PM
 * @Version: 1.0
 * @Description: TODO
 */
@Module
@InstallIn(SingletonComponent::class)
object CustomerServiceModule {

    @Provides
    @Singleton
    fun provideLiveChatProvider(
        @ApplicationContext context: Context
    ): LiveChatProvider {
        return ZendeskLiveChatProviderImpl(context)
    }

    @Provides
    @Singleton
    fun provideHelpCentreProvider(
        @ApplicationContext context: Context
    ): HelpCentreProvider {
        return ZendeskHelpCenterProviderImpl(context)
    }

    @Provides
    @Singleton
    fun provideTicketingProvider(
        @ApplicationContext context: Context
    ): TicketingProvider {
        return ZendeskTicketingProviderImpl(context)
    }
}