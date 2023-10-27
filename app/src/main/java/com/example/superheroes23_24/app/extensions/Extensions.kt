package com.example.superheroes23_24.app.extensions

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.setUrl (urlImage : String){
    Glide
        .with(this)
        .load(urlImage)
        .into(this)
}

fun View.hide(){
    this.visibility = View.GONE
}

fun View.visible(){
    this.visibility = View.VISIBLE
}