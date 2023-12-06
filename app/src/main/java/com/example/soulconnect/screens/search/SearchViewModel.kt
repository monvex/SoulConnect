package com.example.soulconnect.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.soulconnect.SoulConnectViewModel
import com.example.soulconnect.model.User
import com.example.soulconnect.model.service.LogService
import com.example.soulconnect.model.service.SearchService
import com.example.soulconnect.model.service.StorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
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
    var users = searchService.users


    init {
        updateCandidates()
    }


    fun updateCurrentUser() {
        launchCatching {
            uiState.value = uiState.value.copy(currentUser = storageService.getUser())
        }
    }

    fun updateLikeList(likeList: MutableList<String>?){
        uiState.value = uiState.value.copy(candidate = uiState.value.candidate?.copy(likeList = likeList ?: mutableListOf()))
    }

    fun updateCandidate() {
        if(queue.size < 1) {
            updateCandidates()
            uiState.value = uiState.value.copy(candidate = queue.poll())
        }
        uiState.value = uiState.value.copy(candidate = queue.poll())
    }

    fun updateCandidates() {
        launchCatching {
            users = searchService.users
            users.collect {
                uiState.value = uiState.value.copy(candidate = it[0])
                uiState.value = uiState.value.copy(candidatesList = searchService.getSortedCandidates(storageService.getUser(), it))
                uiState.value.candidatesList.forEach {
                    queue.add(it)
                }
            }
        }
    }

    fun updateUserInfo(user: User?) {
        launchCatching {
            if (user != null) storageService.updateUser(user)
        }
    }
}