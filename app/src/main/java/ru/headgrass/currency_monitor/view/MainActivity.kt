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

        binding.btnCurrencyNow.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, CurrencyFragment.newInstance(bundle = Bundle()))
                .commit()
        }

        binding.btnCalculator.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, CalcFragment.newInstance())
                .commit()
        }


    }

}
