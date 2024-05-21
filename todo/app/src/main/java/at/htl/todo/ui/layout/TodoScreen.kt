package at.htl.todo.ui.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import at.htl.todo.model.Model
import at.htl.todo.model.Todo

@Composable
fun Todos(model: Model) {
    val todos = model.todos

    LazyColumn (modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        items(todos.size) { index ->
            DetailTodoRow(todo = todos[index])
        }
    }
}
@Composable
fun DetailTodoRow(todo: Todo) {
    val color: Color = if (todo.completed) Color.LightGray else Color.Black

    Column {
        Row(modifier = Modifier.padding(5.dp)) {
            Text(
                "Id: " + todo.id.toString() ,
                color = color,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize
            )
        }

        Row(modifier = Modifier.padding(5.dp)) {
            Text(
                "Title: " + todo.title,
                color = color,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize
            )
        }

        Row(modifier = Modifier.padding(5.dp)) {
            Text(
                "Completed: " + todo.completed.toString(),
                color = color,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize
            )
        }

        Row(modifier = Modifier.padding(5.dp)) {
            Text(
                "UserId: " + todo.userId.toString(),
                color = color,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize
            )
        }
    }

    HorizontalDivider()
}