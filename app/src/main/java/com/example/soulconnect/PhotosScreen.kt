package com.example.soulconnect

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.soulconnect.navigation.ProfileItem


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PhotosScreen(
    navController: NavController,
    mainImageId: Int?,
    photosClickable: Boolean = true
) {
    var photosIds = listOf(         // TODO: заменить на фотографии из БД
        R.drawable.test_pic_1,
        R.drawable.test_pic_2,
        R.drawable.test_pic_3,
        R.drawable.test_pic_4,
        R.drawable.test_pic_4,
        R.drawable.test_pic_4,
        R.drawable.test_pic_4,
        R.drawable.test_pic_4,

    )
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
        LazyColumn() {

        }
        FlowRow(
            maxItemsInEachRow = 3, modifier = Modifier
                .fillMaxWidth(),
            ) {
            photosIds.forEach {
                Image(painter = painterResource(id = it), contentDescription = "photoId$it", contentScale = ContentScale.Crop, modifier = Modifier
                    .fillMaxWidth(0.33f)
                    .aspectRatio(1f)
                    .border(2.dp, color = Color.White)
                    .clickable(enabled = photosClickable) { navController.navigate(ProfileItem.FullScreenPhoto.getFullRoute(it, mainImageId)) }         // TODO: фотка во весь экран с кнопкой назад
                )
            }
        }
        Button(
            onClick = {}
        ) {
            Text(text = "Click")
        }
    }
}

@Composable
fun FullScreenPhoto(navController: NavController, imageId: Int?, mainImageId: Int?) {
    PhotosScreen(navController = navController, mainImageId = mainImageId, photosClickable = false)
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