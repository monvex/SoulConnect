package com.example.soulconnect.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.soulconnect.ChatsScreen
import com.example.soulconnect.GroupChatsScreen
import com.example.soulconnect.PhotosScreen
import com.example.soulconnect.ProfileScreen
import com.example.soulconnect.SearchScreen

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = BottomItem.Search.route) {
        composable(BottomItem.Search.route) {
            SearchScreen(navHostController)
        }
        composable(BottomItem.Chats.route) {
            ChatsScreen(navHostController)
        }
        composable(BottomItem.GroupChats.route) {
            GroupChatsScreen(navHostController)
        }
        composable(BottomItem.Profile.route) {
            ProfileScreen(navHostController)
        }
        composable(ProfileItem.Photos.route) {

            PhotosScreen(navHostController)
        }
    }
}