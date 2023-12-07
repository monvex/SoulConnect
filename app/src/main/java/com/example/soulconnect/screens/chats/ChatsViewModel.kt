package com.example.soulconnect.screens.chats

import androidx.compose.runtime.mutableStateOf
import com.example.soulconnect.SoulConnectViewModel
import com.example.soulconnect.model.User
import com.example.soulconnect.model.service.AccountService
import com.example.soulconnect.model.service.LogService
import com.example.soulconnect.model.service.SearchService
import com.example.soulconnect.model.service.StorageService
import com.example.soulconnect.screens.log_in.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor(
    private val accountService: AccountService,
    private val storageService: StorageService,
    private val searchService: SearchService,
    logService: LogService
) : SoulConnectViewModel(logService) {
    var uiState = mutableStateOf(ChatsUiState())
        private set

    init {
        updateCurrentUser()
    }

    fun updateCurrentUser() {
        launchCatching {
            uiState.value = uiState.value.copy(currentUser = storageService.getUser())
        }
    }

    fun getInterlocutors(){
        launchCatching {
            val user = storageService.getUser()
            val interlocutorsList: MutableList<User?> = mutableListOf()
            if (user != null) {
                for (interlocutor in user.chats) {
                    val currentInterlocutor = storageService.getUserFromId(interlocutor)
                    interlocutorsList.add(currentInterlocutor)
                }
                uiState.value = uiState.value.copy(interlocutors = interlocutorsList)
            }
        }
    }
}
