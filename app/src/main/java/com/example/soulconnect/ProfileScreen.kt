package com.example.soulconnect

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.soulconnect.text_functions.AutoResizedText

@Composable
fun ProfileScreen() {
    val focusManager = LocalFocusManager.current
    // Основной контейнер
    Column(modifier = Modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            detectTapGestures(
                onTap = { focusManager.clearFocus() }
            )
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Полоска сверху
        Row (
            modifier = Modifier
                .fillMaxHeight(0.05f)
                .fillMaxWidth()
        ){
        }
        // Контейнер с главной фотографией профиля
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f),
            contentAlignment = Alignment.Center
        ) {
            // Тут добавить кнопку для редактирования. Добавлять после Image
            Row() {
                var imageId = remember {
                    mutableStateOf(R.drawable.test_profile_pic)         // Заменить на фотографию из БД
                }
                Image(
                    painter = painterResource(id = imageId.value),
                    contentDescription = "ProfilePic",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(3.dp)
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .clip(CircleShape)
                        .clickable {
                            //Добавить переход на просмотр всех фотографий
                        }
                )
            }

        }
        // Контейнер с именем и возрастом пользователя
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.05f),
            contentAlignment = Alignment.Center
        ) {
            var name = remember {
                mutableStateOf("SomeName")       // Заменить на данные из БД
            }
            var age = remember {
                mutableStateOf(20.toString())       // Заменить на данные из бд
            }
            var text = "${name.value}, ${age.value}"
            AutoResizedText(text = text,
                style = TextStyle(fontSize = 20.sp))
        }
        // Контейнер с городом пользователя
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.05f),
            contentAlignment = Alignment.Center
        ) {
            var city = remember {
                mutableStateOf("SomeCity")      // Заменить на данные из БД
            }
            AutoResizedText(text = city.value,
                style = TextStyle(fontSize = 20.sp)
            )
        }
        // Контейнер с текстовым описанием пользователя, которое можно редактировать
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            ,
            contentAlignment = Alignment.TopCenter
        ) {
            val profileDescription = remember {
                mutableStateOf("Подниму тебя на бицепс и не только ;)")       // Заменить на данные из БД
            }
            val maxCharsInDescription = 180
            var shouldDraw = remember {
                mutableStateOf(true)
            }
            OutlinedTextField(
                value = profileDescription.value,
                onValueChange = {
                                // Добавить загрузку введенных данных в БД
                    if(profileDescription.value.length == maxCharsInDescription)
                        profileDescription.value = maxCharsInDescription.toString()
                    else
                        profileDescription.value = it
                },
                colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                ),
                supportingText = {
                                 Text(
                                     text = "${profileDescription.value.length} / $maxCharsInDescription",
                                     modifier = Modifier.fillMaxWidth().drawWithContent {
                                         if (shouldDraw.value)
                                             drawContent()
                                     },
                                     textAlign = TextAlign.End,
                                 )
                },
                modifier = Modifier
                    .fillMaxSize()
                    .onFocusChanged {
                        shouldDraw.value = !shouldDraw.value
                    }

            )
        }

    }
}