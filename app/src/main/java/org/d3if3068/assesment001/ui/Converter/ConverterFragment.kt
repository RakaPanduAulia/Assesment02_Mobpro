package org.d3if3068.assesment001.ui.Converter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if3068.assesment001.R
import org.d3if3068.assesment001.databinding.FragmentConverterBinding
import org.d3if3068.assesment001.db.ConverterDb


class ConverterFragment : Fragment() {
    private lateinit var editTextCelcius: EditText
    private lateinit var editTextFahrenheit: EditText
    private lateinit var buttonConvert: Button
    private lateinit var textViewResult: TextView
    private lateinit var binding: FragmentConverterBinding
//    private lateinit var viewModel: ConverterViewModel

    private val viewModel: ConverterViewModel by lazy {
        val db = ConverterDb.getInstance(requireContext())
        val factory = ConverterViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[ConverterViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConverterBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextCelcius = binding.editTextCelcius
        editTextFahrenheit = binding.editTextFahrenheit
        buttonConvert = binding.buttonConvert
        textViewResult = binding.textViewResult

//        viewModel = ViewModelProvider(requireActivity()).get(ConverterViewModel::class.java)

        viewModel.data.observe(viewLifecycleOwner, {
            if (it == null) return@observe
            Log.d("HitungFragment", "Data tersimpan. ID = ${it.id}")
        })


        buttonConvert.setOnClickListener {
            val celcius = editTextCelcius.text.toString().toDoubleOrNull()

            if (celcius == null) {
                textViewResult.text = "Masukkan suhu Celcius yang valid"
            } else {
                viewModel.convertTemperature(celcius)
            }
        }

        viewModel.fahrenheitResult.observe(viewLifecycleOwner, Observer { fahrenheit ->
            editTextFahrenheit.setText(fahrenheit.toString())
            textViewResult.text =
                "${viewModel.celciusInput} derajat Celcius = $fahrenheit derajat Fahrenheit"
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_about) {
            findNavController().navigate(
                R.id.action_converterFragment_to_helpFragment
            )
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

