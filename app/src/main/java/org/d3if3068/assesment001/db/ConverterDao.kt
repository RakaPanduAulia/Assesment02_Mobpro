package org.d3if3068.assesment001.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ConverterDao {
    @Insert
    fun insert(converter: ConverterEntity)

    @Query("SELECT * FROM converter ORDER BY id DESC")
    fun getLastConverter(): LiveData<List<ConverterEntity>>

    @Query("DELETE FROM converter")
    fun clearAll()
}