package com.example.soulconnect.screens.chats

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.soulconnect.R

@Composable
fun ChatsScreen(
    onNavigate: (chatId: Int) -> Unit,
    viewModel: ChatsViewModel = hiltViewModel()
) {
    ChatsScreenContent()
}

@Composable
fun ChatsScreenContent(

) {
    Image(
        painter = painterResource(id = R.drawable.start_page_background),
        contentDescription = "background",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
}