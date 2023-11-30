package com.example.soulconnect.screens.tag

import androidx.compose.runtime.mutableStateOf
import com.example.soulconnect.SoulConnectViewModel
import com.example.soulconnect.model.User
import com.example.soulconnect.model.service.AccountService
import com.example.soulconnect.model.service.LogService
import com.example.soulconnect.model.service.StorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TagViewModel @Inject constructor(
    private val accountService: AccountService,
    private val storageService: StorageService,
    logService: LogService
): SoulConnectViewModel(logService) {
    var currentUser = mutableStateOf(User())


    init {
        launchCatching {
            currentUser.value = storageService.getUser() ?: User()
        }
    }


    fun updateUserInfo() {
        launchCatching {
            storageService.updateUser(currentUser.value)
        }
    }

    fun onTagsChange(newValue: MutableList<String>) {
        currentUser.value = currentUser.value.copy(tagList = newValue)
    }
}