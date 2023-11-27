package com.example.soulconnect.screens.photos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PhotosScreen(
    mainImageId: Int,
    userPhotos: List<Int>,
    onNavigate: (id: Int) -> Unit,
    photosClickable: Boolean = true
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
            contentAlignment = Alignment.Center
        ) {
            if(mainImageId != null)
                Image(
                    painter = painterResource(id = mainImageId),
                    contentScale = ContentScale.Crop,
                    contentDescription = "profile_pic",
                    modifier = Modifier.fillMaxSize()
                )
            // TODO добавить код при незагрузившейся картинке
            // else
        }
        FlowRow(
            maxItemsInEachRow = 3, modifier = Modifier
                .fillMaxWidth(),
            ) {
            userPhotos.forEach {
                Image(painter = painterResource(id = it), contentDescription = "photoId$it", contentScale = ContentScale.Crop, modifier = Modifier
                    .fillMaxWidth(0.33f)
                    .aspectRatio(1f)
                    .border(2.dp, color = Color.White)
                    .clickable(enabled = photosClickable) {
                        onNavigate(it)
                    }         // TODO: фотка во весь экран с кнопкой назад
                )
            }
        }
    }
}

@Composable
fun FullScreenPhoto(imageId: Int, mainImageId: Int, userPhotos: List<Int>) {
    PhotosScreen(mainImageId = mainImageId, photosClickable = false, userPhotos = userPhotos, onNavigate = {})
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black.copy(alpha = 0.6f))
    ) {
        if(imageId != null)
            Image(painter = painterResource(id = imageId), contentDescription = "imageFullScreen$imageId", contentScale = ContentScale.Fit, modifier = Modifier
                .fillMaxSize())
    }
}