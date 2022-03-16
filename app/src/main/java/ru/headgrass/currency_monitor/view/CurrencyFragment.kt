package ru.headgrass.currency_monitor.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import ru.headgrass.currency_monitor.model.Currency
import ru.headgrass.currency_monitor.viewmodel.AppState
import ru.headgrass.currency_monitor.viewmodel.CurrencyViewModel
import ru.headgrass.currency_monitor.databinding.CurrencyFragmentBinding

class CurrencyFragment : Fragment() {

    companion object {
        fun newInstance() = CurrencyFragment()
    }

    private var _binding: CurrencyFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CurrencyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CurrencyFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CurrencyViewModel::class.java)
        viewModel.getData().observe(viewLifecycleOwner, { state ->
            render(state)
        })
        viewModel.getCurrency()
    }

    private fun render(state: AppState) {

        when (state) {
            is AppState.Success -> {
                binding.loadingContainer.visibility = View.GONE
                val currency = state.currency as Currency
            }
            is AppState.Error -> {
                binding.loadingContainer.visibility = View.VISIBLE
                Snackbar.make(binding.root, state.error.message.toString(), Snackbar.LENGTH_INDEFINITE)
                    .setAction("Попробовать снова") {
                        viewModel.getCurrency()
                    }.show()
            }
            is AppState.Loading -> {
                binding.loadingContainer.visibility = View.VISIBLE
            }
        }
    }


    //Чтобы избежать утечек памяти
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}