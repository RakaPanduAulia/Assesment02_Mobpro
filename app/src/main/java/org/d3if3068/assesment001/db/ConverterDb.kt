package org.d3if3068.assesment001.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ConverterEntity::class], version = 1, exportSchema = false)
abstract class ConverterDb : RoomDatabase() {
    abstract val dao: ConverterDao

    companion object {

        @Volatile
        private var INSTANCE: ConverterDb? = null

        fun getInstance(context: Context): ConverterDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext, ConverterDb::class.java,
                        "bmi.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}