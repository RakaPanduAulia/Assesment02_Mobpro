package org.d3if3068.assesment001

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import org.d3if3068.assesment001.R

class MainActivity : AppCompatActivity() {

    companion object {
        const val CHANNEL_ID = "updater"
        const val PERMISSION_REQUEST_CODE = 1
    }

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            channel.description =
            getString(R.string.channel_desc)

            val manager = getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager?
            manager?.createNotificationChannel(channel)
        }


        navController = findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() }
}

//    private lateinit var editTextCelcius: EditText
//    private lateinit var editTextFahrenheit: EditText
//    private lateinit var buttonConvert: Button
//    private lateinit var textViewResult: TextView
//    private lateinit var binding: ActivityMainBinding
//    private lateinit var viewModel: TemperatureConverterViewModel
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        editTextCelcius = binding.editTextCelcius
//        editTextFahrenheit = binding.editTextFahrenheit
//        buttonConvert = binding.buttonConvert
//        textViewResult = binding.textViewResult
//
//        viewModel = ViewModelProvider(this).get(TemperatureConverterViewModel::class.java)
//
//        buttonConvert.setOnClickListener {
//            val celcius = editTextCelcius.text.toString().toDoubleOrNull()
//
//            if (celcius == null) {
//                textViewResult.text = "Masukkan suhu Celcius yang valid"
//            } else {
//                viewModel.convertTemperature(celcius)
//            }
//        }
//
//        viewModel.fahrenheitResult.observe(this, Observer { fahrenheit ->
//            editTextFahrenheit.setText(fahrenheit.toString())
//            textViewResult.text = "${viewModel.celciusInput} derajat Celcius = $fahrenheit derajat Fahrenheit"
//        })
//    }

