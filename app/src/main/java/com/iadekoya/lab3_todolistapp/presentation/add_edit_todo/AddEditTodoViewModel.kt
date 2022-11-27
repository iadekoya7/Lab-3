package com.iadekoya.lab3_todolistapp.presentation.add_edit_todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iadekoya.lab3_todolistapp.data.ToDoEntity
import com.iadekoya.lab3_todolistapp.data.TodoRepo
import com.iadekoya.lab3_todolistapp.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val repository: TodoRepo,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    var todo by mutableStateOf<ToDoEntity?>(null)
        private set
    var title by mutableStateOf("")
        private set
    var description by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val todoId = savedStateHandle.get<Int>("todoId")!!
        if(todoId != -1){
            viewModelScope.launch {
                repository.getTodosById(todoId)?.let { todo ->
                    title = todo.title
                    description = todo.description ?: ""
                    this@AddEditTodoViewModel.todo = todo
                }
            }
        }
    }

    fun onEvent(addEditTodoEvent: AddEditTodoEvent){
        when(addEditTodoEvent){
            is AddEditTodoEvent.OnTitleChange -> {
                title = addEditTodoEvent.title
            }
            is AddEditTodoEvent.OnDescriptionChange -> {
                description = addEditTodoEvent.description
            }
            is AddEditTodoEvent.OnSaveTodoClick -> {
                viewModelScope.launch {
                    if(title.isBlank()){
                        sendUiEvent(UiEvent.ShowSnackBar(
                            message = "The title cannot be empty"
                        ))
                        return@launch
                    }
                    repository.insertTodos(
                        ToDoEntity(
                            title = title,
                            description = description,
                            isDone = todo?.isDone ?: false,
                            id = todo?.id
                        )
                    )
                    sendUiEvent(UiEvent.PopBackStack)
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}