package com.guagua.guapay.domain.di

import com.guagua.guapay.domain.card.AddCardUseCase
import com.guagua.guapay.domain.card.AddRandomCardUseCase
import com.guagua.guapay.domain.card.GetCardsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

object DomainModuleProvider {

    val dispatcherModule = module {
        single<CoroutineDispatcher>(DomainQualifier.ioDispatcher) { Dispatchers.IO }
        single<CoroutineDispatcher>(DomainQualifier.defaultDispatcher) { Dispatchers.Default }
        single<CoroutineDispatcher>(DomainQualifier.mainDispatcher) { Dispatchers.Main }
        single<CoroutineDispatcher>(DomainQualifier.mainImmediateDispatcher) { Dispatchers.Main.immediate }
    }

    val domainModules = module {
        factory { GetCardsUseCase(get(DomainQualifier.ioDispatcher), get()) }
        factory { AddCardUseCase(get(DomainQualifier.ioDispatcher), get()) }
        factory { AddRandomCardUseCase(get()) }
    }

    val modules = listOf(
        dispatcherModule,
        domainModules
    )
}