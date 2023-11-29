package com.example.soulconnect.screens.search

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.soulconnect.R
import org.checkerframework.checker.signature.qual.PrimitiveType

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {

    SearchScreenContent()
}



@Composable
fun SearchScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.start_page_background),
            contentDescription = "background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        // Фотка плюс описание поверх фото, потом слайдер добавить
        Box(modifier = Modifier
            .fillMaxHeight(0.6f)
            .fillMaxWidth(0.9f)
            .background(color = Color.Black)
            /* TODO: Добавить реагирование на свайпы влево и вправо, добавить реагирование на тап слева и справа */
        )
        {
            // Город
            /* TODO: Город */
            // Фотография
            /* TODO: Фотография кандидата */
            // Нижняя часть фотографии с описанием кандидата(1 фото - текстовое, второе тэги, дальше тэги)
            /* TODO: Блок с описанием */



        }
    }
}