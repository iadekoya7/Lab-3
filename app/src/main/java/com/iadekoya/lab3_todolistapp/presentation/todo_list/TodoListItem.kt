package com.iadekoya.lab3_todolistapp.presentation.todo_list

import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iadekoya.lab3_todolistapp.data.ToDoEntity

@Composable
fun TodoListItem(
    toDoEntity: ToDoEntity,
    onEvent : (TodoListEvent) -> Unit,
    modifier: Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = toDoEntity.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = {
                    onEvent(TodoListEvent.OnDeleteTodoClick(toDoEntity))
                }) {
                    Icon(imageVector = Icons.Default.Delete,
                        contentDescription = "Delete"
                    )
                }
            }
            toDoEntity.description?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = it)
            }
        }
        Checkbox(checked = toDoEntity.isDone, onCheckedChange = { isChecked ->
            onEvent(TodoListEvent.OnDoneChange(toDoEntity, isDone = isChecked))
        })
    }
}