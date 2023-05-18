package org.d3if3068.assesment001

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.d3if3068.assesment001.databinding.ActivityMainBinding
import org.d3if3068.assesment001.model.TemperatureConverterViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var editTextCelcius: EditText
    private lateinit var editTextFahrenheit: EditText
    private lateinit var buttonConvert: Button
    private lateinit var textViewResult: TextView
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TemperatureConverterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editTextCelcius = binding.editTextCelcius
        editTextFahrenheit = binding.editTextFahrenheit
        buttonConvert = binding.buttonConvert
        textViewResult = binding.textViewResult

        viewModel = ViewModelProvider(this).get(TemperatureConverterViewModel::class.java)

        buttonConvert.setOnClickListener {
            val celcius = editTextCelcius.text.toString().toDoubleOrNull()

            if (celcius == null) {
                textViewResult.text = "Masukkan suhu Celcius yang valid"
            } else {
                viewModel.convertTemperature(celcius)
            }
        }

        viewModel.fahrenheitResult.observe(this, Observer { fahrenheit ->
            editTextFahrenheit.setText(fahrenheit.toString())
            textViewResult.text = "${viewModel.celciusInput} derajat Celcius = $fahrenheit derajat Fahrenheit"
        })
    }
}
