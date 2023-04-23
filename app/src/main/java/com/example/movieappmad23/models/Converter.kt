package com.example.movieappmad23.models

import androidx.room.TypeConverter

class Converter {

    @TypeConverter
     fun genreListConverter(genre: List<Genre>): String {
        return genre.joinToString(separator = ";")
    }

    @TypeConverter
    fun imagesListConverter(images: List<String>): String{
        return images.joinToString(separator = ";")
    }

    @TypeConverter
    fun genreStringConverter(genre: String): List<Genre> {
        return genre.split(";").map{Genre.valueOf(it)}
    }

    @TypeConverter
    fun imagesStringConverter(images: String): List<String>{
        return images.split(";")
    }
}
