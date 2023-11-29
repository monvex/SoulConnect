package com.example.soulconnect.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.soulconnect.screens.chats.ChatsScreen
import com.example.soulconnect.screens.photos.FullScreenPhoto
import com.example.soulconnect.screens.log_in.LogInScreen
import com.example.soulconnect.screens.photos.PhotosScreen
import com.example.soulconnect.screens.profile.ProfileScreen
import com.example.soulconnect.screens.search.SearchScreen
import com.example.soulconnect.SoulConnectAppState
import com.example.soulconnect.screens.group_chats.GroupChatsScreen
import com.example.soulconnect.screens.profile.ProfileItem
import com.example.soulconnect.screens.profile.ProfileViewModel
import com.example.soulconnect.screens.start.StartScreen
import com.example.soulconnect.screens.tag.TagScreen

@Composable
fun NavGraph(
    appState: SoulConnectAppState,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    NavHost(navController = appState.navController, startDestination = "startScreen") {
        composable("startScreen") {
            StartScreen(
                onNavigateToLogIn = {
                    appState.navigate("toLogIn")
                },
                onNavigate = {
                    appState.navigate(BottomItem.Search.route)
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
            GroupChatsScreen()
        }

        logInGraph(appState)       // Граф для авторизации

        profileGraph(appState)     // Граф для профиля
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

fun NavGraphBuilder.logInGraph(appState: SoulConnectAppState) {      // Русик, переход со страницы авторизации
    navigation(
        startDestination = "logIn",
        route = "toLogIn"
    ) {
        composable("logIn"){
            LogInScreen(openAndPopUp = {route, popUp -> appState.navigateAndPopUp(route, popUp)})
        }

    }
}

fun NavGraphBuilder.profileGraph(appState: SoulConnectAppState) {
    navigation(
        startDestination = BottomItem.Profile.route,
        route = "toProfile"
    ) {
        composable(BottomItem.Profile.route) {entry ->
            ProfileScreen(
                onNavigateToTagsScreen = {
                    appState.navigate(ProfileItem.Tags.route)
                },
                onNavigate = {
                    appState.navigate(ProfileItem.Photos.route)
                }
            )
        }
        composable(
            route = ProfileItem.Tags.route
        ){entry ->
            TagScreen()
        }
        composable(
            route = ProfileItem.Photos.route
        ) { navBackStackEntry ->
            val viewModel = navBackStackEntry.sharedViewModel<ProfileViewModel>(appState.navController)
            val mainImageId by viewModel.userMainProfilePic.collectAsStateWithLifecycle()
            val userPhotos by viewModel.userPhotos.collectAsStateWithLifecycle()

            PhotosScreen(
                mainImageId = mainImageId,
                onNavigate = { id ->
                    viewModel.updateChosenPhoto(id)
                    appState.navigate(ProfileItem.FullScreenPhoto.route)
                },
                userPhotos = userPhotos
            )
        }
        composable(
            route = ProfileItem.FullScreenPhoto.route
        ) { navBackStackEntry ->
            val viewModel = navBackStackEntry.sharedViewModel<ProfileViewModel>(appState.navController)
            val mainImageId by viewModel.userMainProfilePic.collectAsStateWithLifecycle()
            val userPhotos by viewModel.userPhotos.collectAsStateWithLifecycle()
            val chosenPhoto by viewModel.chosenPhoto.collectAsStateWithLifecycle()

            FullScreenPhoto(imageId = chosenPhoto, mainImageId = mainImageId, userPhotos = userPhotos)
        }
    }

}