package com.example.perludilindungi.room.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.perludilindungi.room.model.Bookmark

@Database(entities = [Bookmark::class], version = 1, exportSchema = false)
abstract class BookmarkDatabase :RoomDatabase() {
    abstract fun bookmarkDao() : BookmarkDao

    companion object {
        // Singleton
        @Volatile
        private var INSTANCE : BookmarkDatabase? = null

        fun getDatabase(context: Context) : BookmarkDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookmarkDatabase::class.java,
                    "bookmark_database"
                ).build()
                INSTANCE = instance

                instance
            }
        }
    }
}