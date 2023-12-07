package com.example.soulconnect.screens.profile

sealed class ProfileItem(var route: String) {
    object Photos : ProfileItem("photos")
    object Tags : ProfileItem("tags")
    object FullScreenPhoto : ProfileItem("fullScreenPhoto")
}