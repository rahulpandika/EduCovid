package com.capstoneproject.csd190.view.map

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstoneproject.csd190.network.ApiCovid19
import com.capstoneproject.csd190.model.Provence
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MapViewModel : ViewModel() {
    private val data = MutableLiveData<List<Provence>>()
    fun requestData() = viewModelScope.launch(Dispatchers.IO) {
        withContext(Dispatchers.IO) {
            try {
                val result = ApiCovid19.service.getProvinsi()
                data.postValue(result.data)
            } catch (e: Exception) {
                Log.d("MAPS", e.message.toString())
            }
        }
    }

    fun getData(): LiveData<List<Provence>> = data
}