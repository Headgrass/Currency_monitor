package ru.headgrass.currency_monitor.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ru.headgrass.currency_monitor.R
import ru.headgrass.currency_monitor.model.Currency
import ru.headgrass.currency_monitor.viewmodel.AppState
import ru.headgrass.currency_monitor.viewmodel.CurrencyViewModel
import ru.headgrass.currency_monitor.databinding.CurrencyFragmentBinding
import ru.headgrass.currency_monitor.model.CurrencyDTO
import ru.headgrass.currency_monitor.model.CurrencyLoader
import ru.headgrass.currency_monitor.model.MyAdapter

class CurrencyFragment : Fragment() {


    companion object {
        fun newInstance(bundle: Bundle) : CurrencyFragment{
            val fragment = CurrencyFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private var _binding: CurrencyFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter = MyAdapter()

    private val viewModel: CurrencyViewModel by lazy {
        ViewModelProvider(this).get(CurrencyViewModel::class.java)
    }
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<Currency>
    lateinit var nominal: Array<String>
    lateinit var name: Array<String>
    lateinit var value: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CurrencyFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<Currency>("CURRENCY_EXTRA")?.let { currency ->
            binding.currencyRecyclerView.adapter = adapter

            adapter.listener = MyAdapter.OnItemClick {


                activity?.supportFragmentManager?.apply {
                    beginTransaction()
                    .replace(R.id.main_container, CalcFragment())
                    .commit()
                }
            }

            binding.currencyRecyclerView.layoutManager = LinearLayoutManager(requireActivity())


            //Подписались на изменения LiveData
            viewModel.getData().observe(viewLifecycleOwner) { state ->
                render(state)
            }
            //Запросили новые данные
            viewModel.getCurrencyFromLocalStorage()

        }
    }


    private fun render(state: AppState) {

        when (state) {
            is AppState.Success -> {
                val currency: List<Currency> = state.currency
                adapter.setCurrency(currency)

                binding.loadingContainer.visibility = View.GONE
                CurrencyLoader.load(
                    currency = Currency(),
                    object : CurrencyLoader.OnCurrencyLoadListener {
                        override fun onLoaded(currencyDTO: CurrencyDTO) {
                            TODO("Not yet implemented")
                        }

                        override fun onFailed(throwable: Throwable) {
                            TODO("Not yet implemented")
                        }

                    })

            }
            is AppState.Error -> {
                binding.loadingContainer.visibility = View.VISIBLE
                Snackbar.make(
                    binding.root,
                    state.error.message.toString(),
                    Snackbar.LENGTH_INDEFINITE
                )
                    .setAction("Попробовать снова") {
                        viewModel.getCurrencyFromLocalStorage()
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