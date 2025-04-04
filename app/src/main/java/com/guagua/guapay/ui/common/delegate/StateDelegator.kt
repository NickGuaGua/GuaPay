package com.guagua.guapay.ui.common.delegate

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

interface StateDelegator<State> {
    val state: StateFlow<State>
    fun setState(func: (State) -> State)
}

class StateDelegatorImpl<State>(
    initialState: State,
) : StateDelegator<State> {

    private val _state = MutableStateFlow(initialState)
    override val state = _state.asStateFlow()

    override fun setState(func: (State) -> State) {
        _state.value = func(_state.value)
    }
}