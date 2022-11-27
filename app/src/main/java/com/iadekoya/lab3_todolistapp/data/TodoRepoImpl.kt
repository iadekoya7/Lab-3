package com.iadekoya.lab3_todolistapp.data

import kotlinx.coroutines.flow.Flow

class TodoRepoImpl(
    private val dao: ToDoDao
) : TodoRepo {
    override suspend fun insertTodos(todos: ToDoEntity) {
        dao.insertTodos(todos)
    }

    override suspend fun deleteTodos(todos: ToDoEntity) {
        dao.deleteTodos(todos)
    }

    override suspend fun getTodosById(id: Int): ToDoEntity? {
        return dao.getTodosById(id)
    }

    override fun getTodos(): Flow<List<ToDoEntity>> {
        return dao.getTodos()
    }
}