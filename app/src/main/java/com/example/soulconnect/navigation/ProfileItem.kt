package com.example.soulconnect.navigation

import com.example.soulconnect.R

sealed class ProfileItem(var title: String, var route: String) {
    object Photos : ProfileItem("Фотографии", "photos")
    object Tags : ProfileItem("Тэги", "tags")

}