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

    var bookmarksList: LiveData<List<Faskes>> = MutableLiveData()

    fun getBookmarks() {
        viewModelScope.launch {
            bookmarksList = faskesDao.getAll()
        }
    }

}