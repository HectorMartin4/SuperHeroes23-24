package com.example.superheroes23_24.superheroes.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroes23_24.R
import com.example.superheroes23_24.superheroes.domain.GetSuperHeroeFeedUseCase

class SuperHeroesAdapter :
    RecyclerView.Adapter<SuperHeroesViewHolder>() {

    private val dataList: MutableList<GetSuperHeroeFeedUseCase.SuperHeroeFeed> = mutableListOf()

    fun setDataList(superHeroes: List<GetSuperHeroeFeedUseCase.SuperHeroeFeed>){
        dataList.clear()
        dataList.addAll(superHeroes)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroesViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item_super_heroes_feed, parent, false)

        return SuperHeroesViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: SuperHeroesViewHolder, position: Int) {
        holder.render(dataList[position])
    }


}