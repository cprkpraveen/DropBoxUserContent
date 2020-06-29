package com.task.dropboxusercontent.network

import com.task.dropboxusercontent.constants.Constants
import com.task.dropboxusercontent.network.model.CanadaFactsDataModel
import retrofit2.Call
import retrofit2.http.GET

interface CanadaFactsListNetwork {

    @GET(Constants.GET_FACTS)
    fun getCanadaData(): Call<CanadaFactsDataModel>
}