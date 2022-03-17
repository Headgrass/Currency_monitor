package ru.headgrass.currency_monitor.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.headgrass.currency_monitor.R

class CalcFragment : Fragment() {

    companion object {
        fun newInstance() = CalcFragment()
    }

    private lateinit var viewModel: CalcViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.calc_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CalcViewModel::class.java)
        // TODO: Use the ViewModel
    }

}