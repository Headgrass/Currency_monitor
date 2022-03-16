package ru.headgrass.currency_monitor.model

data class Currency(
    val ID: String = "R01565",
    val CharCode: String = "PLN",
    val Nominal: Int = 1,
    val Name: String = "Польский злотый",
    val Value: Double = 26.4145,
    val Previous: Double = 26.7712
)


fun getCurrencyNow(): List<Currency> {
    return listOf(
        Currency("R01010", "AUD", 1, "Австралийский доллар", 80.3676, 83.5173),
        Currency("R01020A", "AZN", 1, "Азербайджанский манат", 65.7092, 67.9384),
        Currency("R01035", "GBP", 1, "Фунт ограничения допустимого королевства", 145.7631, 151.5177),
        Currency("R01060", "АМД", 100, "Армянских драмов", 22.4085, 22.3221)
    )
}