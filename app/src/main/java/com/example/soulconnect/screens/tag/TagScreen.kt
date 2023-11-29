package com.example.soulconnect.screens.tag

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.soulconnect.R
import com.example.soulconnect.screens.profile.ProfileViewModel

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterialApi::class)
@Composable
fun TagScreen(
    viewModel: ProfileViewModel = hiltViewModel()
){
    val currentUser by viewModel.currentUser
    val tagsList = mapOf<String, List<String>>(
        "Творчество" to listOf<String>("Фотография", "Видеосъемка", "Дизайн", "Макияж", "Рукоделие", "Пение", "Танцы", "Музыка", "Блог", "Рисование"),
        "Активный образ жизни" to listOf<String>("Бег", "Фитнес", "Велосипед", "Верховная езда", "Лыжи", "Йога", "Пилатес", "Сноуборд", "Ролики", "Скейтборд", "Самокат")
    )
    Image(
        painter = painterResource(id = R.drawable.start_page_background),
        contentDescription = "background",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
    Column(modifier = Modifier.fillMaxSize()) //TODO: изменить на LazyColumn()
    {
        for ((key, value) in tagsList){
            Box(modifier = Modifier.padding(5.dp)) {
                Text(
                    text = "$key",
                    fontFamily = FontFamily(
                        Font(
                            R.font.relay_comfortaa_regular,
                            weight = FontWeight.W400,
                            style = FontStyle.Normal
                        )
                    ),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }
            FlowRow(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                for (tag in value) {
                    FilterChip(selected = false, onClick = {}) {
                        Text(text = "$tag")
                    }
                }
            }

        }
        FlowRow(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            for (tag in currentUser.tagList) {
                FilterChip(selected = false, onClick = {}) {
                    Text(text = "$tag")
                }
            }
        }
    }
}