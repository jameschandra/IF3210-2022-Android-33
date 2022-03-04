package com.example.perludilindungi.ui.faskes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.perludilindungi.models.CityResponse
import com.example.perludilindungi.models.FaskesResponse
import com.example.perludilindungi.models.ProvinceResponse
import com.example.perludilindungi.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class FaskesListViewModel(application: Application) : AndroidViewModel(application) {

    val faskesList: MutableLiveData<Response<FaskesResponse>> = MutableLiveData()
    val provinceList: MutableLiveData<Response<ProvinceResponse>> = MutableLiveData()
    val cityList: MutableLiveData<Response<CityResponse>> = MutableLiveData()
    private val repository: Repository = Repository()

    fun getFaskesList(province: String, city: String) {
        // TODO: get 5 closest faskes to user location
        viewModelScope.launch {
            val response = repository.getFaskes(province, city)
            faskesList.value = response
        }
    }

    fun getProvince() {
        viewModelScope.launch {
            val response = repository.getProvince()
            provinceList.value = response
        }
    }

    fun getCity(province: String) {
        viewModelScope.launch {
            val response = repository.getCity(province)
            cityList.value = response
        }
    }


}