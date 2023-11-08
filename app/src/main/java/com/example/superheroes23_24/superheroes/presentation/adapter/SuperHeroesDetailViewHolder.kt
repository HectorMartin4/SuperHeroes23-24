package com.example.superheroes23_24.superheroes.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroes23_24.app.extensions.setUrl
import com.example.superheroes23_24.databinding.ViewItemImageDetailBinding

class SuperHeroesDetailViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun render(img: String){
        val binding = ViewItemImageDetailBinding.bind(view)

        binding.apply {
            image.setUrl(img)
        }
    }
}