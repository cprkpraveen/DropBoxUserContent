package com.task.dropboxusercontent.repository

import android.app.Application
import org.junit.Before
import org.junit.Test
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.task.dropboxusercontent.constants.Constants
import com.task.dropboxusercontent.network.CanadaFactsListNetwork
import com.task.dropboxusercontent.network.NetworkConnectionInterceptor
import com.task.dropboxusercontent.network.RetrofitAPIClient
import com.task.dropboxusercontent.network.model.CanadaFactsDataModel
import com.task.dropboxusercontent.viewmodel.CanadaFactsListViewModel
import okhttp3.OkHttpClient
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CanadaFactsListActivityRepositoryTest {

    private lateinit var canadaFactsListRepository: CanadaFactsListRepository
    @Mock
    private lateinit var context: Application
    private lateinit var canadaFactsListViewModel: CanadaFactsListViewModel

    @Mock
    private lateinit var callback: Callback<CanadaFactsDataModel>
    private val canadaData = MutableLiveData<CanadaFactsDataModel>()
    private val progressBar = MutableLiveData<Boolean>()
    private var mRetrofit: Retrofit? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        canadaFactsListRepository = CanadaFactsListRepository(context)
        `when`<Context>(context.applicationContext).thenReturn(context)
        canadaFactsListViewModel = CanadaFactsListViewModel(context)
        RetrofitAPIClient.getRetrofitClient(context)
        val oktHttpClient = OkHttpClient.Builder()
            .addInterceptor(NetworkConnectionInterceptor(context))
        mRetrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(oktHttpClient.build())
            .build()
    }

    @Test
    fun fetchCanadaData() {
        val service = mRetrofit?.create(CanadaFactsListNetwork::class.java)
        service?.getCanadaData()?.enqueue(callback)
    }
}