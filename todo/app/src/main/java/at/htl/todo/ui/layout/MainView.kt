package at.htl.todo.ui.layout

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import at.htl.todo.model.Model
import at.htl.todo.model.ModelStore
import at.htl.todo.model.Todo
import at.htl.todo.ui.theme.TodoTheme
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainView @Inject constructor() {

    @Inject
    lateinit var store: ModelStore

    fun buildContent(activity: ComponentActivity) {
        activity.enableEdgeToEdge()
        activity.setContent {
            val viewModel = store
                .pipe
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeAsState(initial = Model())
                .value
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Todos(model = viewModel, modifier = Modifier.padding(all = 32.dp), store = store)
            }
        }
    }

    @Composable
    fun Todos(model: Model, modifier: Modifier = Modifier, store: ModelStore) {
        val todos = model.todos

        LazyColumn(
            modifier = modifier.padding(10.dp)
        ) {
            items(todos.size) { index ->
                TodoRow(todo  = todos[index], store = store)
                HorizontalDivider()
            }
        }
    }

    @Composable
    fun TodoRow(todo: Todo, store: ModelStore) {
        val color: Color = if (todo.completed) Color.Green else Color.Red

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = todo.id.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = color
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = todo.title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = color
                )
            }

            Column {
                Checkbox(
                    checked = todo.completed,
                    colors = CheckboxDefaults.colors(
                        checkmarkColor = Color.White,
                        checkedColor = color,
                        uncheckedColor = color
                    ),
                    onCheckedChange = {
                        /* Update the completed status of the todo item */
                        store.updateCompletionOfTodo(todo.id, !todo.completed)
                    }
                )
            }
        }
    }
}
