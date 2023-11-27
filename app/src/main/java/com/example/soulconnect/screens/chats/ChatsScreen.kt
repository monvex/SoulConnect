package com.example.soulconnect.screens.chats

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.soulconnect.R

@Composable
fun ChatsScreen(onNavigate: (chatId: Int) -> Unit) {
    Image(
        painter = painterResource(id = R.drawable.start_page_background),
        contentDescription = "background",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
    val names = listOf(
        "Дима",
        "Руслан",
        "Артем",
        "Леонид"
    )
    val pics = listOf(
        R.drawable.test_pic_4,
        R.drawable.test_pic_1,
        R.drawable.test_pic_2,
        R.drawable.test_pic_3
    )
    val text = listOf(
        "Го дрифтить",
        "Че по матеше?",
        "Скинешь лабу?",
        "Hey"
    )

    Column() {
        for(i in 0..3) {
            Chat(picId = pics[i], name = names[i], text = text[i])
        }
    }
}