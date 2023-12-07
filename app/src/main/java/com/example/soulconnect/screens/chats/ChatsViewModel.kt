package com.example.soulconnect.screens.chats

import androidx.compose.runtime.mutableStateOf
import com.example.soulconnect.SoulConnectViewModel
import com.example.soulconnect.model.service.AccountService
import com.example.soulconnect.model.service.LogService
import com.example.soulconnect.screens.log_in.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: LogService
) : SoulConnectViewModel(logService) {
    var uiState = mutableStateOf(ChatsUiState())
        private set

    private val smth
        get() = uiState.value.smth
}
