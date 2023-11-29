package com.example.soulconnect.screens.search

import androidx.compose.runtime.mutableStateOf
import com.example.soulconnect.SoulConnectViewModel
import com.example.soulconnect.model.User
import com.example.soulconnect.model.service.AccountService
import com.example.soulconnect.model.service.LogService
import com.example.soulconnect.model.service.SearchService
import com.example.soulconnect.model.service.StorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val storageService: StorageService,
    private val searchService: SearchService,
    logService: LogService
): SoulConnectViewModel(logService) {
    val currentUser = mutableStateOf(User())

}