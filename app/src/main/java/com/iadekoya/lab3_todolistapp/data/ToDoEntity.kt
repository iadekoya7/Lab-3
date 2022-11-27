package com.iadekoya.lab3_todolistapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDoEntity(
    val title: String,
    val description: String?,
    val isDone: Boolean,
    @PrimaryKey val id: Int? = null
)
