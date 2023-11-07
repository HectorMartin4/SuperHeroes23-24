package com.example.superheroes23_24.app.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.setUrl (urlImage : String){
    Glide
        .with(this)
        .load(urlImage)
        .into(this)
}