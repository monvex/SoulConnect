package com.example.soulconnect.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.soulconnect.ChatsScreen
import com.example.soulconnect.FullScreenPhoto
import com.example.soulconnect.LogInScreen
import com.example.soulconnect.PhotosScreen
import com.example.soulconnect.ProfileScreen
import com.example.soulconnect.SearchScreen
import com.example.soulconnect.StartScreen
import com.example.soulconnect.TagScreen

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = "startScreen") {
        composable("startScreen") {
            StartScreen(
                onNavigateToLogIn = {
                    navHostController.navigate("toLogIn")
                },
                onNavigate = {
                    navHostController.navigate(BottomItem.Search.route)
                }
            )
        }
        composable(BottomItem.Search.route) {
            SearchScreen()
        }
        composable(BottomItem.Chats.route) {
            ChatsScreen(onNavigate = {      /* TODO: перенаправление в нужный чат */

            })
        }
        composable(BottomItem.GroupChats.route) {
            LogInScreen(onNavigate = {})
        }
        composable("toLogIn"){
            LogInScreen(onNavigate = {})
        }
        profileGraph(navHostController)
    }

}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController,
): T {
    val navGraphRoute = destination.parent?.route?: return viewModel()
    val parentEntry = remember(this) {
        navController. getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}


fun NavGraphBuilder.profileGraph(navHostController: NavHostController) {
    navigation(
        startDestination = BottomItem.Profile.route,
        route = "toProfile"
    ) {
        composable(BottomItem.Profile.route) {entry ->
            val viewModel = entry.sharedViewModel<ProfileViewModel>(navHostController)
            ProfileScreen(
                onNavigateToTagsScreen = {
                    navHostController.navigate(ProfileItem.Tags.route)
                },
                onNavigate = {
                    viewModel.updateUserPhotos()
                    navHostController.navigate(ProfileItem.Photos.route)
                }
            )
        }
        composable(
            route = ProfileItem.Tags.route
        ){entry ->
            val viewModel = entry.sharedViewModel<ProfileViewModel>(navHostController)
            val state by viewModel.userTagList.collectAsStateWithLifecycle()
            TagScreen(navHostController, state)
        }
        composable(
            route = ProfileItem.Photos.route
        ) { navBackStackEntry ->
            val viewModel = navBackStackEntry.sharedViewModel<ProfileViewModel>(navHostController)
            val mainImageId by viewModel.userMainProfilePic.collectAsStateWithLifecycle()
            val userPhotos by viewModel.userPhotos.collectAsStateWithLifecycle()

            PhotosScreen(
                mainImageId = mainImageId,
                onNavigate = { id ->
                    viewModel.updateChosenPhoto(id)
                    navHostController.navigate(ProfileItem.FullScreenPhoto.route)
                },
                userPhotos = userPhotos
            )
        }
        composable(
            route = ProfileItem.FullScreenPhoto.route
        ) { navBackStackEntry ->
            val viewModel = navBackStackEntry.sharedViewModel<ProfileViewModel>(navHostController)
            val mainImageId by viewModel.userMainProfilePic.collectAsStateWithLifecycle()
            val userPhotos by viewModel.userPhotos.collectAsStateWithLifecycle()
            val chosenPhoto by viewModel.chosenPhoto.collectAsStateWithLifecycle()

            FullScreenPhoto(imageId = chosenPhoto, mainImageId = mainImageId, userPhotos = userPhotos)
        }
    }

}