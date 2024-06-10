package com.tihonyakovlev.rehabilitationapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sections")
data class Section(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)