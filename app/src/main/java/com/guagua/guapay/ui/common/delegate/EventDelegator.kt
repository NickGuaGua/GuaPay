package com.guagua.guapay.ui.common.delegate

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface EventDelegator<Event> {
    val event: StateFlow<Event?>
    suspend fun send(event: Event)
    fun consumeEvent()
}

class EventDelegatorImpl<Event> : EventDelegator<Event> {

    private val _event = MutableStateFlow<Event?>(null)
    override val event = _event

    override suspend fun send(event: Event) {
        _event.emit(event)
    }

    override fun consumeEvent() {
        _event.value = null
    }
}