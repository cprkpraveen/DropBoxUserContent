package com.task.dropboxusercontent.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.task.dropboxusercontent.network.model.CanadaFactsDataModel
import com.task.dropboxusercontent.repository.CanadaFactsListRepository

class CanadaFactsListViewModel(application:Application) : AndroidViewModel(application){

    private val repository = CanadaFactsListRepository(application)
    val canadaFactsData : LiveData<CanadaFactsDataModel>
    val progressBar : LiveData<Boolean>

    init {
        this.canadaFactsData = repository.canadaData
        this.progressBar = repository.progressBar
    }

    fun fetchCanadaData(){
        repository.fetchCanadaData()
    }

}