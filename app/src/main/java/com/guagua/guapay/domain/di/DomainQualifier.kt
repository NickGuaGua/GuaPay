package com.guagua.guapay.domain.di

import org.koin.core.qualifier.named

object DomainQualifier {
    val ioDispatcher = named("io_dispatcher")
    val defaultDispatcher = named("default_dispatcher")
    val mainDispatcher = named("main_dispatcher")
    val mainImmediateDispatcher = named("main_immediate_dispatcher")
}