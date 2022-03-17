package ru.headgrass.currency_monitor.model

interface Repository {

    fun getCurrencyFromServer(): Currency

    fun getCurrencyFromLocalStorage(): List<Currency>
}