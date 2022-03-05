package com.example.perludilindungi.ui.bookmark

import androidx.lifecycle.*
import com.example.perludilindungi.data.Faskes
import com.example.perludilindungi.data.FaskesDao
import com.example.perludilindungi.models.FaskesData
import com.example.perludilindungi.models.FaskesResponse
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Response

class BookmarkViewModel(private val faskesDao: FaskesDao) : ViewModel() {

    var bookmarksList: Flow<List<FaskesData>> = emptyFlow()

    fun getBookmarks() {
        viewModelScope.launch {
            val result = faskesDao.getAll()
            bookmarksList = flow { result.first().map { mapperComplicateToFaskesData(it) } }
        }
    }

    fun mapperComplicateToFaskesData(faskes: Faskes): FaskesData {
        return FaskesData(
            faskes.id,
            faskes.kode,
            faskes.nama,
            "",
            "",
            faskes.alamat,
            faskes.latitude,
            faskes.telp,
            faskes.jenis_faskes,
            "",
            faskes.status,
            "",
            ArrayList(),
            "",
        )
    }

}