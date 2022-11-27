package com.iadekoya.lab3_todolistapp.presentation.todo_list

import com.iadekoya.lab3_todolistapp.data.ToDoEntity

sealed class TodoListEvent{
    data class OnDeleteTodoClick(val todo: ToDoEntity) : TodoListEvent()
    data class OnDoneChange(val todo: ToDoEntity, val isDone: Boolean): TodoListEvent()
    object OnUndoDelectClick: TodoListEvent()
    data class OnTodoClick(val todo: ToDoEntity): TodoListEvent()
    object OnAddTodoClick: TodoListEvent()
}
