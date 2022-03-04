package com.example.perludilindungi.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FaskesDao {
    @Query("SELECT * FROM faskes")
    fun getAll(): LiveData<List<Faskes>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(faskes: Faskes)

    @Delete
    suspend fun delete(faskes: Faskes)
}