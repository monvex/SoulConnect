package com.example.soulconnect.navigation

import com.example.soulconnect.R

sealed class BottomItem(var title: String, var iconId: Int, var route: String) {
    object Search : BottomItem("Поиск", R.drawable.ic_search, "search")
    object Chats : BottomItem("Чаты", R.drawable.ic_chats, "chats")
    object GroupChats : BottomItem("Групповые\nЧаты", R.drawable.ic_groupchats, "group_chats")
    object Profile : BottomItem("Профиль", R.drawable.ic_profile, "profile")

}