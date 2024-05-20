package at.htl.posts.ui.layout

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import at.htl.posts.model.Model
import at.htl.posts.model.ModelStore
import at.htl.posts.model.entity.Post
import at.htl.posts.model.entity.User
import at.htl.posts.model.entity.UserDetail
import at.htl.posts.ui.theme.PostsTheme
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
                Posts(model = viewModel, modifier = Modifier.padding(all = 40.dp), activity = activity)
            }
        }
    }

    @Composable
    fun Posts(model: Model, modifier: Modifier = Modifier, activity: ComponentActivity) {
        val state = store.pipe.map { it }.subscribeAsState(initial = Model())
        val posts = model.posts

        LazyColumn(
            modifier = modifier.padding(16.dp)
        ) {
            items(posts.size) { index ->
                PostRow(post = posts[index])
            }
        }

        if (state.value.userDetail.showUserDetail) {
            UserDetailScreen(user = state.value.userDetail.selectedUser!!, context = activity, store = store)
        }
    }

    @SuppressLint("CheckResult")
    @Composable
    fun PostRow(post: Post) {
        val state = store.pipe.map {it}.subscribeAsState(initial = Model())

        var user: User = User()

        store.pipe.map { it.users }.subscribe {
            if(it.find { u -> u.id == post.userId } != null) {
                user = it.find { u -> u.id == post.userId }!!
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(270.dp),
                elevation = CardDefaults.cardElevation(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Button(
                                onClick = {
                                    store.apply {
                                        it.userDetail.showUserDetail = true
                                        it.userDetail.selectedUser = user
                                    } },
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.AccountCircle,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(end = 5.dp, start = 5.dp)
                                )

                                Text(
                                    text = user.username,
                                    modifier = Modifier
                                        .padding(end = 5.dp, start = 5.dp)
                                )
                            }
                        }
                    }

                    Text(
                        text = post.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(8.dp)
                    )

                    Text(
                        text = post.body,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}
