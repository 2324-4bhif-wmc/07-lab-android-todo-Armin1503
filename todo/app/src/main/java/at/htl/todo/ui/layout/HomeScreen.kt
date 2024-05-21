package at.htl.todo.ui.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import at.htl.todo.model.Model
import at.htl.todo.model.ModelStore
import at.htl.todo.model.Todo
import at.htl.todo.model.TodoService

@Composable
fun HomeScreen(model: Model, todoService: TodoService?, store: ModelStore?) {
    val todos = model.todos;

    LazyColumn(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        items(todos.size) { index ->
            if (store != null) {
                TodoRow(todo  = todos[index], store = store)
            }
            HorizontalDivider()
        }
    }

}

@Composable
fun TodoRow(todo: Todo, store: ModelStore) {
    val color: Color = if (todo.completed) Color.LightGray else Color.Black

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = todo.title,
                style = MaterialTheme.typography.headlineSmall,
                color = color
            )
        }

        Column {
            Checkbox(
                checked = todo.completed,
                onCheckedChange = {
                    /* Update the completed status of the todo item */
                    store.updateCompletionOfTodo(todo.id, !todo.completed)
                }
            )
        }
    }
}