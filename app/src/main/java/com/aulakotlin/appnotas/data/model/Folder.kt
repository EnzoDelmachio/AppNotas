package com.aulakotlin.appnotas.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "folders")
data class Folder (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String
)