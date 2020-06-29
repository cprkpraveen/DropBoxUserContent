package com.task.dropboxusercontent.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.task.dropboxusercontent.R
import com.task.dropboxusercontent.network.model.CanadaFactsRow
import kotlinx.android.synthetic.main.canada_list_item.view.*

class CanadaFactsDataAdapter(private val context: Context) :
    RecyclerView.Adapter<CanadaFactsDataAdapter.ViewHolder>() {
    private var list: List<CanadaFactsRow> = ArrayList()

    fun setCanadaDataList(list: List<CanadaFactsRow>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.canada_list_item, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = list[position].title
        holder.description.text = list[position].description
        Glide.with(context).asDrawable().load(list[position].imageHref).into(holder.image)
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val title = v.id_title!!
        val description = v.id_description!!
        val image = v.id_image!!
    }

}