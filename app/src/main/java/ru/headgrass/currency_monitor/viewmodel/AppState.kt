package ru.headgrass.currency_monitor.viewmodel

import ru.headgrass.currency_monitor.model.Currency

sealed class AppState {
    data class Success(val currency: List<Currency>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
