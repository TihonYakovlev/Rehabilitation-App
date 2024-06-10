package com.tihonyakovlev.rehabilitationapp.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "diseases")
data class Disease(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val sectionId: Int,
    val name: String,
    val descriptionFile: String,
    val exercisesFile: String
)
