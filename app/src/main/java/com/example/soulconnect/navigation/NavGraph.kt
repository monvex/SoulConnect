package com.example.soulconnect.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.soulconnect.ChatsScreen
import com.example.soulconnect.FullScreenPhoto
import com.example.soulconnect.GroupChatsScreen
import com.example.soulconnect.PhotosScreen
import com.example.soulconnect.ProfileScreen
import com.example.soulconnect.SearchScreen
import com.example.soulconnect.TagScreen

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
        // Перенос аргументов в
        composable(
            route = ProfileItem.Photos.route,
            arguments = listOf(
                navArgument(name = IMAGE_ID) {
                    type = NavType.IntType
                }
            )
            ) { navBackStackEntry ->
            val imageId = navBackStackEntry.arguments?.getInt(IMAGE_ID)
            PhotosScreen(navController = navHostController, mainImageId = imageId)
        }
        composable(
            route = ProfileItem.FullScreenPhoto.route,
            arguments = listOf(
                navArgument(name = IMAGE_ID) {
                    type = NavType.IntType
                },
                navArgument(name = MAIN_IMAGE_ID) {
                    type = NavType.IntType
                }
            )
        ) { navBackStackEntry ->
            val imageId = navBackStackEntry.arguments?.getInt(IMAGE_ID)
            val mainImageId = navBackStackEntry.arguments?.getInt(MAIN_IMAGE_ID)
            FullScreenPhoto(navController = navHostController, imageId = imageId, mainImageId)
        }
        composable(
            route = ProfileItem.Tags.route,
            arguments = listOf(
                navArgument(name = TAG_LIST){
                    type = NavType.StringArrayType
                }
            )
        ){navBackStackEntry ->
            val tagList = navBackStackEntry.arguments?.getStringArrayList(TAG_LIST)
            TagScreen(navHostController, tagList)
        }

    }
}