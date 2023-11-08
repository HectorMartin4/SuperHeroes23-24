package com.example.superheroes23_24.superheroes.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroes23_24.app.extensions.setUrl
import com.example.superheroes23_24.databinding.ViewItemSuperHeroesFeedBinding
import com.example.superheroes23_24.superheroes.domain.GetSuperHeroeFeedUseCase

class SuperHeroesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    val binding = ViewItemSuperHeroesFeedBinding.bind(view)
    fun render(
        superHeroe: GetSuperHeroeFeedUseCase.SuperHeroeFeed,
        onClickDetail: ((Int) -> Unit)?
    ) {

        binding.apply {
            image.setUrl(superHeroe.image)
            title.text = superHeroe.name
            name.text = superHeroe.fullName
            occupation.text = superHeroe.occupation
            icNavigate.setOnClickListener {
                onClickDetail!!.invoke(superHeroe.id)

            }
        }
    }
}