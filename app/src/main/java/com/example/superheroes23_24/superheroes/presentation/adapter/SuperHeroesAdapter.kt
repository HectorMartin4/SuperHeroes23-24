package com.example.superheroes23_24.superheroes.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.superheroes23_24.R
import com.example.superheroes23_24.superheroes.domain.GetSuperHeroeFeedUseCase
import com.example.superheroes23_24.superheroes.presentation.SuperHeroesDiffUtil

class SuperHeroesAdapter : ListAdapter<GetSuperHeroeFeedUseCase.SuperHeroeFeed, SuperHeroesViewHolder>(SuperHeroesDiffUtil()) {

    private var onClickDetail: ((Int) -> Unit)? = null

    fun setOnClickDetail(onClickDetail: ((Int) -> Unit)){
        this.onClickDetail = onClickDetail
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroesViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item_super_heroes_feed, parent, false)

        return SuperHeroesViewHolder(view)
    }

    override fun getItemCount(): Int = currentList.size

    override fun onBindViewHolder(holder: SuperHeroesViewHolder, position: Int) {
        holder.render(currentList[position], onClickDetail)
    }


}