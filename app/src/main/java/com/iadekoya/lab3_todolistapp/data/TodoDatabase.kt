package com.iadekoya.lab3_todolistapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ToDoEntity::class],
    version = 1
)
abstract class TodoDatabase : RoomDatabase() {
    abstract val todoDao : ToDoDao
}