package org.d3if3068.assesment001

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDatabaseHelper(private val context: Context?) : SQLiteOpenHelper(
    context, DATABASE_NAME, null, DATABASE_VERSION
) {
    override fun onCreate(db: SQLiteDatabase) {
        val query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CELCIUS + " TEXT, " +
                COLUMN_FAHRENHEIT + " TEXT);"
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, i: Int, i1: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun addHistory(celcius: String?, fahrenheit: String?) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_CELCIUS, celcius)
        cv.put(COLUMN_FAHRENHEIT, fahrenheit)
        val result = db.insert(TABLE_NAME, null, cv)
        if (result == -1L) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }

    fun readAllData(): Cursor? {
        val query = "SELECT * FROM " + TABLE_NAME
        val db = this.readableDatabase
        var cursor: Cursor? = null
        if (db != null) {
            cursor = db.rawQuery(query, null)
        }
        return cursor
    }

    fun deleteDataById(id: String): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, COLUMN_ID + "=?", arrayOf(id))
    }

    companion object {
        private const val DATABASE_NAME = "HistoryDatabase.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "history"
        private const val COLUMN_ID = "_id"
        private const val COLUMN_CELCIUS = "celcius"
        private const val COLUMN_FAHRENHEIT = "fahrenheit"
    }
}