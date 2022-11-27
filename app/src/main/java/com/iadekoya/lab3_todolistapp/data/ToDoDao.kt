package com.iadekoya.lab3_todolistapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodos(todos: ToDoEntity)

    @Delete
    suspend fun deleteTodos(todos: ToDoEntity)

    @Query("SELECT * FROM todoentity WHERE id = :id")
    suspend fun getTodosById(id: Int) : ToDoEntity?

    @Query("SELECT * FROM todoentity")
    fun getTodos() : Flow<List<ToDoEntity>>
}