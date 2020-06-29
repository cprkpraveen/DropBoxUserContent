package com.task.dropboxusercontent.network

import android.content.Context
import com.task.dropboxusercontent.constants.Constants
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient

object RetrofitAPIClient {
    private var retrofit: Retrofit? = null

    fun getRetrofitClient(mContext: Context): Retrofit? {
        if (retrofit == null) {
            val oktHttpClient = OkHttpClient.Builder()
                .addInterceptor(NetworkConnectionInterceptor(mContext))

            retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(oktHttpClient.build())
                .build()

        }
        return retrofit
    }
}