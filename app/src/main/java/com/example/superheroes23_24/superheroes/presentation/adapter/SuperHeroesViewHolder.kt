package com.example.superheroes23_24.superheroes.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroes23_24.app.extensions.setUrl
import com.example.superheroes23_24.databinding.ActivitySuperHeroesFeedBinding
import com.example.superheroes23_24.databinding.ViewItemSuperHeroesFeedBinding
import com.example.superheroes23_24.superheroes.domain.GetSuperHeroeFeedUseCase

class SuperHeroesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun render(superHeroe: GetSuperHeroeFeedUseCase.SuperHeroeFeed) {
        val binding = ViewItemSuperHeroesFeedBinding.bind(view)

        binding.apply {
            image.setUrl(superHeroe.image)
            title.text = superHeroe.name
            name.text = superHeroe.fullName
            occupation.text = superHeroe.occupation
        }
    }
}