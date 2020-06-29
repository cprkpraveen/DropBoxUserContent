package com.task.dropboxusercontent.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.dropboxusercontent.R
import com.task.dropboxusercontent.adapter.CanadaFactsDataAdapter
import com.task.dropboxusercontent.constants.Constants
import com.task.dropboxusercontent.viewmodel.CanadaFactsListViewModel
import kotlinx.android.synthetic.main.activity_canada_facts.*

class CanadaFactsListActivity : AppCompatActivity() {
    private lateinit var viewModel : CanadaFactsListViewModel
    private lateinit var adapterFacts : CanadaFactsDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_canada_facts)
        supportActionBar?.title = Constants.EMPTY
        initializeView()
        swipeRefreshView.setOnRefreshListener {
            getData()
        }

        viewModel.progressBar.observe(this, Observer {
            if(it){
                swipeRefreshView.isRefreshing = false
            }
        })

        viewModel.canadaFactsData.observe(this, Observer {
            supportActionBar?.title = it.title
            adapterFacts.setCanadaDataList(it.rows)
            swipeRefreshView.isRefreshing = false
        })
        initializeAdapter()
    }

    private fun initializeView(){
        swipeRefreshView.isRefreshing = true
        viewModel = ViewModelProviders.of(this).get(CanadaFactsListViewModel::class.java)
        rv_canadaFacts.layoutManager = LinearLayoutManager(this)
        getData()
    }

    private fun getData(){
        viewModel.fetchCanadaData()
    }

    private fun initializeAdapter(){
        adapterFacts = CanadaFactsDataAdapter(this)
        rv_canadaFacts.adapter = adapterFacts
    }
}
