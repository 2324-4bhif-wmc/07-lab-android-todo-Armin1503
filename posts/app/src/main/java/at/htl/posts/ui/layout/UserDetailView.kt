package at.htl.posts.ui.layout

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import at.htl.posts.model.ModelStore
import at.htl.posts.model.entity.User
import at.htl.posts.util.store.Store
import androidx.compose.material.Surface
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import at.htl.posts.MainActivity
import at.htl.posts.R

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun UserDetailScreen(user: User, context: Context, store: ModelStore) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        BoxWithConstraints(modifier = Modifier.weight(1f)) {
            Surface(color = MaterialTheme.colorScheme.background) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                ) {
                    ShowReturnBtn(context, store)

                    ShowProfileHeader(user)

                    ShowUserInformation(user)
                }
            }
        }
    }
}

@Composable
private fun ShowUserInformation(user: User){
    val cardHeight = 80.dp
    val cardBorderWidth = 1.dp
    val cardBorderColor = MaterialTheme.colorScheme.primary
    val cardCornerShapeSize = 20.dp

    Row(modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 15.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            androidx.compose.material.Text(
                text = "Information",
                fontWeight = FontWeight.Bold,
                fontSize = TextUnit(28f, TextUnitType.Sp),
                modifier = Modifier
                    .padding(bottom = 10.dp)
            )

            Row(
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Card(
                        border = BorderStroke(cardBorderWidth, cardBorderColor),
                        shape = RoundedCornerShape(cardCornerShapeSize),
                        modifier = Modifier
                            .height(cardHeight)
                            .fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Column(modifier = Modifier.padding(5.dp)) {
                                Image(
                                    imageVector = Icons.Rounded.Email,
                                    contentDescription = null,
                                    modifier = Modifier.size(30.dp)
                                )
                            }

                            Column(modifier = Modifier.padding(5.dp)) {
                                Row {
                                    Text(
                                        text = user.email,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = TextUnit(20f, TextUnitType.Sp)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Row(
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Card(
                        border = BorderStroke(cardBorderWidth, cardBorderColor),
                        shape = RoundedCornerShape(cardCornerShapeSize),
                        modifier = Modifier
                            .height(cardHeight)
                            .fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.Center
                        ){
                            Column(
                                modifier = Modifier.padding(5.dp)
                            ) {
                                Image(
                                    imageVector = Icons.Rounded.Phone,
                                    contentDescription = null,
                                    modifier = Modifier.size(30.dp)
                                )
                            }

                            Column(
                                modifier = Modifier.padding(5.dp)) {
                                Row{
                                    Text(
                                        text = user.phone,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = TextUnit(20f, TextUnitType.Sp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ShowReturnBtn(context: Context, store: ModelStore){
    Row(modifier = Modifier.padding(top = 20.dp)) {
        IconButton(onClick = {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)

            store.apply {
                it.userDetail.selectedUser = User()
                it.userDetail.showUserDetail = false
            }
        }) {
            androidx.compose.material3.Icon(
                modifier = Modifier
                    .size(40.dp)
                    .padding(bottom = 10.dp),
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun ShowProfileHeader(user: User) {
    Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = user.name,
                fontWeight = FontWeight.Bold,
                fontSize = TextUnit(28f, TextUnitType.Sp)
            )

            Text(
                text = user.username,
                color = Color.Gray,
                fontSize = TextUnit(25f, TextUnitType.Sp)
            )
        }

        Column {
            Image(
                painter = painterResource(id = R.drawable.profile_pic),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(80.dp))
            )
        }
    }

    androidx.compose.material3.Divider(thickness = 1.dp, color = Color.LightGray)
}