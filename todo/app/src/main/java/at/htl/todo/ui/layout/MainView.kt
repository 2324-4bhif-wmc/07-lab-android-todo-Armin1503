package at.htl.todo.ui.layout

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import at.htl.todo.model.Model
import at.htl.todo.model.ModelStore
import at.htl.todo.model.TodoService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainView @Inject constructor() {

    @Inject
    lateinit var store: ModelStore

    @Inject
    lateinit var todoService: TodoService

    fun setContentOfActivity(activity: ComponentActivity) {
        todoService.getAll();

        val view = ComposeView(activity)
        view.setContent {
            val viewModel = store
                .pipe
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeAsState(initial = Model())
                .value

            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                TabScreen(viewModel, store, todoService)
            }
        }
        activity.setContentView(view)
    }

    /*
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
    */
}
