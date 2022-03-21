package ru.headgrass.currency_monitor.model

import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

object CurrencyLoader {

    fun load(currency: Currency, listener: OnCurrencyLoadListener) {

        val handler = Handler(Looper.myLooper() ?: Looper.getMainLooper())

        Thread {
            var urlConnection: HttpsURLConnection? = null
            try {
                val uri = URL("https://www.cbr-xml-daily.ru/daily_json.js")

                urlConnection = uri.openConnection() as HttpsURLConnection
                urlConnection.requestMethod = "GET"
                urlConnection.readTimeout = 1000
                urlConnection.connectTimeout = 1000
                val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                val result = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    reader.lines().collect(Collectors.joining("\n"))
                } else {
                    ""
                }
                Log.d("DEBUGLOG", "result: $result")

                val currencyDTO = Gson().fromJson(result, CurrencyDTO::class.java)
                handler.post {
                    listener.onLoaded(currencyDTO)
                }

            } catch (e: Exception) {
                listener.onFailed(e)
                Log.e("DEBUGLOG", "FAIL CONNECTION", e)
            } finally {
                urlConnection?.disconnect()
            }
        }.start()
    }

    interface OnCurrencyLoadListener {
        fun onLoaded(currencyDTO: CurrencyDTO)
        fun onFailed(throwable: Throwable)
    }
}