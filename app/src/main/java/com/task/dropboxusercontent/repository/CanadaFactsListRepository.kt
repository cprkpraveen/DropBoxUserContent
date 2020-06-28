package com.task.dropboxusercontent.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.task.dropboxusercontent.constants.Constants
import com.task.dropboxusercontent.network.*
import com.task.dropboxusercontent.network.model.CanadaFactsDataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CanadaFactsListRepository(val application: Application) {

    val canadaData = MutableLiveData<CanadaFactsDataModel>()
    val progressBar = MutableLiveData<Boolean>()

    fun fetchCanadaData(){
        val retrofit = RetrofitAPIClient.getRetrofitClient(application)
        val service = retrofit?.create(CanadaFactsListNetwork::class.java)

        service?.getCanadaData()?.enqueue(object : Callback<CanadaFactsDataModel>{

            override fun onFailure(call: Call<CanadaFactsDataModel>, t: Throwable) {
                // log error
                if(t is NoConnectivityException){
                    progressBar.value = true
                    val toast = Toast.makeText(application, Constants.CONNECTION_ERROR, Toast.LENGTH_SHORT)
                    toast.show()
                }
            }

            override fun onResponse(call: Call<CanadaFactsDataModel>, response: Response<CanadaFactsDataModel>) {
                canadaData.value = response.body()
            }
        })
    }
}