package org.d3if3068.assesment001

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.d3if3068.assesment001.databinding.ActivityMainBinding
import org.d3if3068.assesment001.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var editTextCelcius: EditText
    private lateinit var editTextFahrenheit: EditText
    private lateinit var buttonConvert: Button
    private lateinit var textViewResult: TextView
    private lateinit var buttonHistory: FloatingActionButton
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editTextCelcius = binding.editTextCelcius
        editTextFahrenheit = binding.editTextFahrenheit
        buttonConvert = binding.buttonConvert
        buttonHistory = binding.buttonHistory
        textViewResult = binding.textViewResult

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val myDB = MyDatabaseHelper(this)

        buttonConvert.setOnClickListener {
            val celsius = editTextCelcius.text.toString().toDoubleOrNull()

            if (celsius == null) {
                textViewResult.text = "Masukkan suhu Celcius yang valid"
            } else {
                val fahrenheit = viewModel.convertTemperature(celsius)
                editTextFahrenheit.setText(fahrenheit.toString())
                textViewResult.text = "$celsius derajat Celcius = $fahrenheit derajat Fahrenheit"

                myDB.addHistory(celsius.toString(), fahrenheit.toString())
            }
        }

        buttonHistory.setOnClickListener {
            val intent = Intent(this@MainActivity, HistoryActivity::class.java)
            startActivity(intent)
        }
    }
}

//            val myDB = MyDatabaseHelper(this@MainActivity)
//            myDB.addHistory(celsius.text.toString().trim(), fahrenheit.text.toString().trim())

//            @Override
//            public void onClick(View view) {
//                MyDatabaseHelper myDB = new MyDatabaseHelper(MainActivity.this);
//                myDB.addHistory(celcius.getText().toString().trim(),
//                        fahrenheit.getText().toString().trim());
//            }