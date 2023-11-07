package com.example.superheroes23_24.superheroes.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.superheroes23_24.superheroes.domain.GetSuperHeroeFeedUseCase

class SuperHeroesDiffUtil : DiffUtil.ItemCallback<GetSuperHeroeFeedUseCase.SuperHeroeFeed>() {
    override fun areItemsTheSame(
        oldItem: GetSuperHeroeFeedUseCase.SuperHeroeFeed,
        newItem: GetSuperHeroeFeedUseCase.SuperHeroeFeed
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: GetSuperHeroeFeedUseCase.SuperHeroeFeed,
        newItem: GetSuperHeroeFeedUseCase.SuperHeroeFeed
    ): Boolean {
        return oldItem == newItem
    }
}