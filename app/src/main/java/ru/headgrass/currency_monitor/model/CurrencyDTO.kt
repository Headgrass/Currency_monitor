package ru.headgrass.currency_monitor.model

import com.google.gson.annotations.SerializedName

data class CurrencyDTO(
    val Valute: HashMap<String, DetailCurrencyDTO>
)

data class DetailCurrencyDTO(
    @SerializedName("ID_id")
    val ID: String,
    @SerializedName("NumCode_numCode")
    val NumCode: Int,
    @SerializedName("CharCode_charCode")
    val CharCode: String,
    @SerializedName("Nominal_nominal")
    val Nominal: Int,
    @SerializedName("Name_name")
    val Name: String,
    @SerializedName("Value_value")
    val Value: Double,
    @SerializedName("Previous_previous")
    val Previous: Double
)
