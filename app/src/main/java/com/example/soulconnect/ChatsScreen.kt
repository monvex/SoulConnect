package com.example.soulconnect

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun ChatsScreen(navController: NavController) {
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
    var pics = listOf(
        R.drawable.test_pic_4,
        R.drawable.test_pic_1,
        R.drawable.test_pic_2,
        R.drawable.test_pic_3
    )
    var text = listOf(
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