package com.example.soulconnect.screens.group_chats

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.soulconnect.model.User
import com.example.soulconnect.screens.log_in.LoginViewModel

@Composable
fun GroupChatsScreen(viewModel: LoginViewModel = hiltViewModel()) {
    Row() {
        val curUser = viewModel.currentUser.collectAsStateWithLifecycle(User())
        Text(text = curUser.value.toString())
    }
}