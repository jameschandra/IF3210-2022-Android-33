package com.example.perludilindungi.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Faskes::class], version = 1, exportSchema = false)
abstract class FaskesDatabase : RoomDatabase() {
    abstract val faskesDao : FaskesDao

    companion object {
        @Volatile
        private var INSTANCE: FaskesDatabase? = null

        fun getDatabase(context: Context): FaskesDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FaskesDatabase::class.java,
                    "faskes_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}