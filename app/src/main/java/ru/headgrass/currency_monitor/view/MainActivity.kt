package ru.headgrass.currency_monitor.view

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.ListFragment
import ru.headgrass.currency_monitor.R
import ru.headgrass.currency_monitor.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCurrencyNow.setOnClickListener({
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, CurrencyFragment.newInstance())
                .commit()
        })

        binding.btnCalculator.setOnClickListener({
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, CalcFragment.newInstance())
                .commit()
        })

        Thread {
            var urlConnection: HttpsURLConnection? = null
            val handler = Handler(Looper.getMainLooper())

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
                handler.post {
                    
                }

            } catch (e: Exception) {
                Log.e("DEBUGLOG", "FAIL CONNECTION", e)
            } finally {
                urlConnection?.disconnect()
            }
        }.start()
    }

}
