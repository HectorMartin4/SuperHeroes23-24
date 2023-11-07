package com.example.superheroes23_24.superheroes.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroes23_24.R

class SuperHeroesDetailAdapter: RecyclerView.Adapter<SuperHeroesDetailViewHolder>() {

    private val dataItems = mutableListOf<String>()

    fun loadItems(imgs: List<String>) {
        dataItems.addAll(imgs)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroesDetailViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item_image_detail, parent, false)

        return SuperHeroesDetailViewHolder(view)
    }

    override fun getItemCount(): Int = dataItems.size

    override fun onBindViewHolder(holder: SuperHeroesDetailViewHolder, position: Int) {
        holder.render(dataItems[position])

    }
}