package com.example.soulconnect.navigation

import androidx.lifecycle.ViewModel
import com.example.soulconnect.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

const val IMAGE_ID = "image_id"
const val MAIN_IMAGE_ID = "main_image_id"
const val TAG_LIST = "tag_list"
sealed class ProfileItem(var title: String, var route: String) {
    object Photos : ProfileItem("Фотографии", "photos" + "/{$IMAGE_ID}") {
        fun getFullRoute(imageId: Int?): String {
            return "photos" + "/$imageId"
        }
    }
    object Tags : ProfileItem("Тэги", "tags"){
        fun getFullRoute(tagList: List<String>?): String{
            return "tags" + "/$tagList"
        }
    }
    object FullScreenPhoto : ProfileItem("БольшоеФото", "fullScreenPhoto" + "/{$IMAGE_ID}" + "/{$MAIN_IMAGE_ID}") {
        fun getFullRoute(imageId: Int?, mainImageId: Int?): String {
            return "fullScreenPhoto" + "/$imageId" + "/$mainImageId"
        }
    }

}

class ProfileViewModel : ViewModel() {
    // Список тэгов пользователя
    private val _userTagList = MutableStateFlow(emptyList<String>())
    var userTagList = _userTagList.asStateFlow()

    // Обновление списка тегов пользователя
    fun updateUserTagList(tags: List<String>) {     // Переписать под получение данных из БД
        _userTagList.value = _userTagList.value.plus(tags)
    }
}