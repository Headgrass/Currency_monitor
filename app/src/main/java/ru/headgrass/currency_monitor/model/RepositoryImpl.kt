package ru.headgrass.currency_monitor.model

class RepositoryImpl : Repository {
    override fun getCurrencyFromServer(): Currency = Currency()

    override fun getCurrencyFromLocalStorage(): List<Currency> = getCurrencyNow()
}