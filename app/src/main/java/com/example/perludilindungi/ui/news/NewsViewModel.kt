package com.example.perludilindungi.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perludilindungi.models.NewsResponse
import com.example.perludilindungi.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(private val repository : Repository) : ViewModel() {

    val newsResponse: MutableLiveData<Response<NewsResponse>>  = MutableLiveData()

    fun getNews(){
        viewModelScope.launch {
            val response = repository.getNews()
            newsResponse.value = response
        }
    }
}