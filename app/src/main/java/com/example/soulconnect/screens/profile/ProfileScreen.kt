package com.example.soulconnect.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.soulconnect.R
import com.example.soulconnect.text_functions.AutoResizedText
import com.google.relay.compose.tappable

@OptIn(
    ExperimentalMaterialApi::class,
    ExperimentalLayoutApi::class,
    ExperimentalGlideComposeApi::class,
)
@Composable
fun ProfileScreen(
    onNavigateToTagsScreen: () -> Unit,
    onNavigate: () -> Unit,
    onStartScreen: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val focusManager = LocalFocusManager.current
    val user by viewModel.currentUser
    // Основной контейнер
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.start_page_background),
            contentDescription = "background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = { focusManager.clearFocus() },
                    )
                },
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Полоска сверху
            Row(
                modifier = Modifier
                    .fillMaxHeight(0.05f)
                    .fillMaxWidth(),
            ) {
                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        textAlign = TextAlign.End,
                        text = "Выйти",
                        color = Color.Red,
                        modifier = Modifier
                            .padding(10.dp, 5.dp)
                            .tappable(onTap = {
                                viewModel.onLogout()
                                onStartScreen()
                            }),
                    )
                }
            }

            // Контейнер с главной фотографией профиля
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.2f),
                contentAlignment = Alignment.Center,
            ) {
                // Тут добавить кнопку для редактирования. Добавлять после Image
                Row() {
                    val imageId = remember {
                        mutableStateOf(R.drawable.test_profile_pic) // Заменить на фотографию из БД
                    }
                    GlideImage(
                        model = user.avatar,
                        contentDescription = "ProfilePic",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(3.dp)
                            .fillMaxHeight()
                            .aspectRatio(1f)
                            .clip(CircleShape)
                            .clickable {
                                onNavigate()
                            },
                    )
                }
            }

            // Контейнер с именем и возрастом пользователя
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.05f),
                contentAlignment = Alignment.Center,
            ) {
                val text = "${user.name}, ${user.age}"
                AutoResizedText(
                    text = text,
                    style = TextStyle(fontSize = 20.sp, color = Color.White),
                )
            }
            // Контейнер с городом пользователя
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.05f),
                contentAlignment = Alignment.Center,
            ) {
                AutoResizedText(
                    text = user.city,
                    style = TextStyle(fontSize = 20.sp, color = Color.White), // Заменить на данные из БД
                )
            }
            // Контейнер с текстовым описанием пользователя, которое можно редактировать
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp),
                contentAlignment = Alignment.TopCenter,
            ) {
                val maxCharsInDescription = 180
                val shouldDraw = remember {
                    mutableStateOf(true)
                }

                OutlinedTextField(
                    value = user.description,
                    onValueChange = {
                        if (it.length <= maxCharsInDescription) {
                            viewModel.onDescriptionChange(it)
                        }
                        viewModel.updateUserInfo()
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color.White,
                    ),
                    supportingText = {
                        Text(
                            text = "${user.description.length} / $maxCharsInDescription",
                            modifier = Modifier
                                .fillMaxWidth()
                                .drawWithContent {
                                    if (shouldDraw.value) {
                                        drawContent()
                                    }
                                },
                            textAlign = TextAlign.End,
                            color = Color.White,
                        )
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .onFocusChanged {
                            shouldDraw.value = !shouldDraw.value
                            viewModel.getUserInfo()
                        },
                )
            }

            // Контейнер с кнопкой редактирования тэгов
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                IconButton(onClick = onNavigateToTagsScreen) {
                    androidx.compose.material3.Icon(imageVector = Icons.Outlined.Info, contentDescription = "someshit")
                }
            }
            // Контейнер с тэгами пользователя
            // TODO Довести до ума поле с тегами
            FlowRow(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp, end = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                user.tagList.forEach {
                    val selected = remember {
                        mutableStateOf(false)
                    }
                    FilterChip(
                        selected = selected.value,
                        onClick = { selected.value = !selected.value },
                    ) {
                        Text(text = it)
                    }
                }
            }
        }
    }
}