package com.example.perludilindungi.repository

import androidx.annotation.WorkerThread
import com.example.perludilindungi.room.data.BookmarkDao
import com.example.perludilindungi.room.model.Bookmark
import kotlinx.coroutines.flow.Flow

class BookmarkRepository(private val bookmarkDao: BookmarkDao) {
    val allBookmarks: Flow<List<Bookmark>> = bookmarkDao.getAllBookmarks()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(bookmark: Bookmark) {
        bookmarkDao.insert(bookmark)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(bookmark: Bookmark) {
        bookmarkDao.delete(bookmark)
    }
}