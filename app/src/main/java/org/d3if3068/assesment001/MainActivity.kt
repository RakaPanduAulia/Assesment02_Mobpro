package org.d3if3068.assesment001

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import org.d3if3068.assesment001.databinding.ActivityMainBinding
import org.d3if3068.assesment001.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var editTextCelcius: EditText
    private lateinit var editTextFahrenheit: EditText
    private lateinit var buttonConvert: Button
    private lateinit var textViewResult: TextView
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editTextCelcius = binding.editTextCelcius
        editTextFahrenheit = binding.editTextFahrenheit
        buttonConvert = binding.buttonConvert
        textViewResult = binding.textViewResult

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        buttonConvert.setOnClickListener {
            val celsius = editTextCelcius.text.toString().toDoubleOrNull()

            if (celsius == null) {
                textViewResult.text = "Masukkan suhu Celcius yang valid"
            } else {
                val fahrenheit = viewModel.convertTemperature(celsius)
                editTextFahrenheit.setText(fahrenheit.toString())
                textViewResult.text = "$celsius derajat Celcius = $fahrenheit derajat Fahrenheit"
            }
        }
    }
}