package org.d3if3068.assesment001

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.d3if3068.assesment001.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var editTextCelcius: EditText
    private lateinit var editTextFahrenheit: EditText
    private lateinit var buttonConvert: Button
    private lateinit var textViewResult: TextView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        R.layout.activity_main

        editTextCelcius = binding.editTextCelcius
        editTextFahrenheit = binding.editTextFahrenheit
        buttonConvert = binding.buttonConvert
        textViewResult = binding.textViewResult

        buttonConvert.setOnClickListener {
            val celcius = editTextCelcius.text.toString().toDoubleOrNull()

            if (celcius == null) {
                textViewResult.text = "Masukkan suhu Celcius yang valid"
            } else {
                val fahrenheit = (celcius * 9 / 5) + 32
                editTextFahrenheit.setText(fahrenheit.toString())
                textViewResult.text = "$celcius derajat Celcius = $fahrenheit derajat Fahrenheit"
            }
        }
    }
}