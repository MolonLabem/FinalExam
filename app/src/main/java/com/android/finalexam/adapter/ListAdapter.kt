package com.android.finalexam.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.finalexam.R
import com.android.finalexam.model.Country
import kotlinx.android.synthetic.main.item.view.*

class ListAdapter(private val countryClickListener: (post: Country) -> Unit) :
RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var countryList: MutableList<Country> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ContactViewHolder(inflater, parent)
    }

    override fun getItemCount() = countryList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ContactViewHolder).bind(countryList[position], countryClickListener)
    }

    fun addItems(list: List<Country>) {
        countryList.clear()
        countryList.addAll(list)
        notifyDataSetChanged()
    }

    private class ContactViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item, parent, false)) {

        private val title = itemView.postTitle
        private val postLayout = itemView.postLayout

        fun bind(country: Country, countryClickListener: (post: Country) -> Unit) {
            title.text = country.countryName
            postLayout.setOnClickListener {
                countryClickListener(country)
            }
        }
    }
}