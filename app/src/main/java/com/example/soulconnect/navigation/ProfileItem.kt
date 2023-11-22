package com.example.soulconnect.navigation

import com.example.soulconnect.R
const val IMAGE_ID = "image_id"
const val MAIN_IMAGE_ID = "main_image_id"
const val TAG_LIST = "tag_list"
sealed class ProfileItem(var title: String, var route: String) {
    object Photos : ProfileItem("Фотографии", "photos" + "/{$IMAGE_ID}") {
        fun getFullRoute(imageId: Int?): String {
            return "photos" + "/$imageId"
        }
    }
    object Tags : ProfileItem("Тэги", "tags" + "/{$TAG_LIST}"){
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