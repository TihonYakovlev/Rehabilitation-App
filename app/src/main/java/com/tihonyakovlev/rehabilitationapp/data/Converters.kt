package com.tihonyakovlev.rehabilitationapp.data

import androidx.room.TypeConverter

// Converters.kt
class Converters {
    @TypeConverter
    fun fromStringList(value: List<String>): String = value.joinToString(separator = ",")

    @TypeConverter
    fun toStringList(value: String): List<String> = value.split(",")
}
