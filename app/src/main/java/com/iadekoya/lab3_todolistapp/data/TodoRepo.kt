package com.iadekoya.lab3_todolistapp.data

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface TodoRepo {

    suspend fun insertTodos(todos: ToDoEntity)

    suspend fun deleteTodos(todos: ToDoEntity)

    suspend fun getTodosById(id: Int) : ToDoEntity?

    fun getTodos() : Flow<List<ToDoEntity>>
}