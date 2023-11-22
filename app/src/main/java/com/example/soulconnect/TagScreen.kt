package com.example.soulconnect

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagScreen(
    navController: NavController,
    tagList: List<String>?
){
    val tagsList = mapOf<String, List<String>>(
        "Творчество" to listOf<String>("Фотография", "Видеосъемка", "Дизайн", "Макияж", "Рукоделие", "Пение", "Танцы", "Музыка", "Блог", "Рисование"),
        "Активный образ жизни" to listOf<String>("Бег", "Фитнес", "Велосипед", "Верховная езда", "Лыжи", "Йога", "Пилатес", "Сноуборд", "Ролики", "Скейтборд", "Самокат")
    )
    Column(modifier = Modifier.fillMaxSize()) //TODO: изменить на LazyColumn()
    {
        for ((key, value) in tagsList){
            Text(text = "$key $value")
        }
    }
}