package com.example.perludilindungi.ui.faskes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.perludilindungi.models.FaskesResponse
import com.example.perludilindungi.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class FaskesListViewModel(application: Application) : AndroidViewModel(application) {

    val faskesList: MutableLiveData<Response<FaskesResponse>> = MutableLiveData()
    private val repository : Repository = Repository()

    fun getFaskesList(province: String, city: String){
        viewModelScope.launch {
            val response = repository.getFaskes(province, city)
            faskesList.value = response
        }
    }
}