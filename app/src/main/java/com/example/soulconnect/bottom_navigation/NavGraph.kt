package com.example.soulconnect.bottom_navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.soulconnect.ChatsScreen
import com.example.soulconnect.GroupChatsScreen
import com.example.soulconnect.ProfileScreen
import com.example.soulconnect.SearchScreen

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = BottomItem.Search.route) {
        composable(BottomItem.Search.route) {
            SearchScreen()
        }
        composable(BottomItem.Chats.route) {
            ChatsScreen()
        }
        composable(BottomItem.GroupChats.route) {
            GroupChatsScreen()
        }
        composable(BottomItem.Profile.route) {
            ProfileScreen()
        }
    }
}