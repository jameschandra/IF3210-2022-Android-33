package com.example.perludilindungi.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FaskesDao {
    @Query("SELECT * FROM faskes")
    fun getAll(): Flow<List<Faskes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(faskes: Faskes)

    @Delete
    suspend fun delete(faskes: Faskes)
}