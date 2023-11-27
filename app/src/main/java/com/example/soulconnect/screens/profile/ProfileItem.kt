package com.example.soulconnect.screens.profile

import androidx.lifecycle.ViewModel
import com.example.soulconnect.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed class ProfileItem(var route: String) {
    object Photos : ProfileItem("photos")
    object Tags : ProfileItem("tags")
    object FullScreenPhoto : ProfileItem("fullScreenPhoto")

}

