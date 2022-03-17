package ru.headgrass.currency_monitor.model

class RepositoryImpl : Repository {
    override fun getCurrencyFromServer(): Currency {
        return Currency()
    }

    override fun getCurrencyFromLocalStorage(): List<Currency> {
        return getCurrencyNow()
    }
}