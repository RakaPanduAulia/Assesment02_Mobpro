package org.d3if3068.assesment001;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    MyDatabaseHelper myDB;
    ArrayList<String> history_id, history_celcius, history_fahrenheit;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.recyclerView);

        myDB = new MyDatabaseHelper(HistoryActivity.this);
        history_id = new ArrayList<>();
        history_celcius = new ArrayList<>();
        history_fahrenheit = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(HistoryActivity.this, history_id, history_celcius, history_fahrenheit);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));

    }
    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                history_id.add(cursor.getString(0));
                history_celcius.add(cursor.getString(1));
                history_fahrenheit.add(cursor.getString(2));
            }
        }
    }
    // Tambahkan method deleteData()
//    void deleteData(int position) {
//        int result = myDB.deleteData(history_id.get(position));
//        if (result > 0) {
//            Toast.makeText(this, "Data deleted successfully", Toast.LENGTH_SHORT).show();
//            history_id.remove(position);
//            history_celcius.remove(position);
//            history_fahrenheit.remove(position);
//            customAdapter.notifyItemRemoved(position);
//        } else {
//            Toast.makeText(this, "Failed to delete data", Toast.LENGTH_SHORT).show();
//        }
//    }
}
