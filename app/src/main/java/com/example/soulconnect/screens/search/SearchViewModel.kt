package com.example.soulconnect.screens.search

import androidx.compose.runtime.mutableStateOf
import com.example.soulconnect.SoulConnectViewModel
import com.example.soulconnect.model.User
import com.example.soulconnect.model.service.LogService
import com.example.soulconnect.model.service.SearchService
import com.example.soulconnect.model.service.StorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.LinkedList
import java.util.Queue
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val storageService: StorageService,
    private val searchService: SearchService,
    logService: LogService
): SoulConnectViewModel(logService) {

    private val queue: Queue<User> = LinkedList()
    var uiState = mutableStateOf(SearchUiState())
        private set



    fun updateCurrentUser() {
        launchCatching {
            uiState.value = uiState.value.copy(currentUser = storageService.getUser())
        }
    }
    fun updateCandidate() {
        if(queue.size < 2) {
            updateCandidates()
            uiState.value = uiState.value.copy(candidate = queue.poll())
        }
        uiState.value = uiState.value.copy(candidate = queue.poll())
    }

    fun updateCandidates() {
        launchCatching {
            uiState.value = uiState.value.copy(candidatesList = searchService.getSortedCandidates(uiState.value.currentUser, searchService.getUsers()))
        }
        uiState.value.candidatesList.forEach {
            queue.add(it)
        }
    }

}