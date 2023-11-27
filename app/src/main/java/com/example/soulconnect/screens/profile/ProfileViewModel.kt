package com.example.soulconnect.screens.profile

import androidx.lifecycle.ViewModel
import com.example.soulconnect.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel : ViewModel() {
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
    fun updateUserMainProfilePic(id: Int) {
        _userMainProfilePic.value = id
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