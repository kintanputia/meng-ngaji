package com.example.meng_ngaji.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.meng_ngaji.nearby.ModelResults
import com.example.meng_ngaji.network.ApiInterface
import com.example.meng_ngaji.network.ApiMaps
import com.example.meng_ngaji.response.ModelResultNearby
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MasjidViewModel : ViewModel() {

    private val modelResultMutableLiveData = MutableLiveData<ArrayList<ModelResults>>()
    var strApiKey = "google_maps_key"
    
    fun setMarkerLocation(strLocation: String) {
        val apiService: ApiInterface = ApiMaps.getMaps()
        
        val call = apiService.getDataResult(strApiKey, "Masjid", strLocation, "distance")
        call.enqueue(object : Callback<ModelResultNearby> {
            override fun onResponse(
                call: Call<ModelResultNearby>,
                response: Response<ModelResultNearby>
            ) {
                if (!response.isSuccessful){
                    Log.e("response", response.toString())
                } else if (response.body() != null) {
                    val items = ArrayList(response.body()!!.modelResults)
                    modelResultMutableLiveData.postValue(items)
                }
            }

            override fun onFailure(call: Call<ModelResultNearby>, t: Throwable) {
                Log.e("failure", t.toString())
            }

        })
    }

    fun getMarkerLocation(): LiveData<ArrayList<ModelResults>> = modelResultMutableLiveData
}