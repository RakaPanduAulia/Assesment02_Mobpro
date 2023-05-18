package org.d3if3068.assesment001

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryActivity : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var myDB: MyDatabaseHelper? = null
    var history_id: ArrayList<String>? = null
    var history_celcius: ArrayList<String>? = null
    var history_fahrenheit: ArrayList<String>? = null
    var customAdapter: CustomAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        recyclerView = findViewById(R.id.recyclerView)
        myDB = MyDatabaseHelper(this@HistoryActivity)
        history_id = ArrayList()
        history_celcius = ArrayList()
        history_fahrenheit = ArrayList()
        storeDataInArrays()
        customAdapter = CustomAdapter(
            this@HistoryActivity,
            history_id!!,
            history_celcius!!,
            history_fahrenheit!!
        )
        recyclerView.setAdapter(customAdapter)
        recyclerView.setLayoutManager(LinearLayoutManager(this@HistoryActivity))
    }

    fun storeDataInArrays() {
        val cursor = myDB!!.readAllData()
        if (cursor.count == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show()
        } else {
            while (cursor.moveToNext()) {
                history_id!!.add(cursor.getString(0))
                history_celcius!!.add(cursor.getString(1))
                history_fahrenheit!!.add(cursor.getString(2))
            }
        }
}