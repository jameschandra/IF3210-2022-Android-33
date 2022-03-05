package com.example.perludilindungi.ui.faskes

import android.app.Application
import android.location.Location
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.perludilindungi.data.Faskes
import com.example.perludilindungi.models.CityResponse
import com.example.perludilindungi.models.FaskesData
import com.example.perludilindungi.models.FaskesResponse
import com.example.perludilindungi.models.ProvinceResponse
import com.example.perludilindungi.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class FaskesListViewModel(application: Application) : AndroidViewModel(application) {

    val faskesList: MutableLiveData<List<FaskesData>> = MutableLiveData()
    val provinceList: MutableLiveData<Response<ProvinceResponse>> = MutableLiveData()
    val cityList: MutableLiveData<Response<CityResponse>> = MutableLiveData()
    private val repository: Repository = Repository()

    fun getFaskesList(province: String, city: String) {
        // TODO: get 5 closest faskes to user location
        viewModelScope.launch {
            val response = repository.getFaskes(province, city)
            faskesList.value = response.body()?.data
        }
    }

    fun getFaskesList(province: String, city: String, lat: Double, lon: Double) {
        viewModelScope.launch {
            val response = repository.getFaskes(province, city)
            val allFaskes = response.body()?.data?.mapIndexed { idx, faskes ->
                arrayOf(
                    faskes.latitude.toDouble(),
                    faskes.longitude.toDouble(),
                    idx.toDouble()
                )
            }?.toMutableList()
            val closestIdx = ArrayList<Int>()

            for (i in 0..5) {
                val results = FloatArray(2)
                val nextClosest = allFaskes?.minByOrNull { it ->
                    Location.distanceBetween(lat, lon, it[0], it[1], results)
                    results[0]
                }
                closestIdx.add(nextClosest?.get(2)?.toInt()!!)
                allFaskes.remove(nextClosest)
            }

            val filteredFaskes = response.body()?.data?.filterIndexed { idx, _ ->
                closestIdx.contains(idx)
            }

            faskesList.value = filteredFaskes!!
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