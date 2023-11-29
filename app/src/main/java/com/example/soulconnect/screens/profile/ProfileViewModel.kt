package com.example.soulconnect.screens.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.soulconnect.R
import com.example.soulconnect.SoulConnectViewModel
import com.example.soulconnect.model.User
import com.example.soulconnect.model.service.AccountService
import com.example.soulconnect.model.service.LogService
import com.example.soulconnect.model.service.StorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
@HiltViewModel
class ProfileViewModel @Inject constructor(
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



    // Список тэгов пользователя
    private val _userTagList = MutableStateFlow(emptyList<String>())
    var userTagList = _userTagList.asStateFlow()

    // Id главной фотографии в профиле
    private val _userMainProfilePic = MutableStateFlow(R.drawable.test_profile_pic)
    var userMainProfilePic = _userMainProfilePic.asStateFlow()

    // Список id всех фотографий пользователя
    private val _userPhotos = MutableStateFlow(emptyList<Int>())
    var userPhotos = _userPhotos.asStateFlow()

    // Id выбранной фотки в профиле
    private val _chosenPhoto = MutableStateFlow(R.drawable.test_profile_pic)
    var chosenPhoto = _chosenPhoto.asStateFlow()

    // Обновление списка тегов пользователя
    fun updateUserTagList(tags: List<String>) {     // Переписать под получение данных из БД
        _userTagList.value = tags
    }


    fun updateUserPhotos() {            // Переписать под получение данных из БД
        _userPhotos.value = listOf(
            R.drawable.test_pic_1,
            R.drawable.test_pic_2,
            R.drawable.test_pic_3,
            R.drawable.test_pic_4
        )
    }

    fun updateChosenPhoto(id: Int) {
        _chosenPhoto.value = id
    }
}