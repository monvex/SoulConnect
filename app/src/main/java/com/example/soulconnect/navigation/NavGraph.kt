package com.example.soulconnect.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.soulconnect.ChatsScreen
import com.example.soulconnect.FullScreenPhoto
import com.example.soulconnect.GroupChatsScreen
import com.example.soulconnect.PhotosScreen
import com.example.soulconnect.ProfileScreen
import com.example.soulconnect.navigation.ProfileViewModel
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
        navigation(
            startDestination = "toProfile",
            route = BottomItem.Profile.route
        ) {
            composable("toProfile") {entry ->
                val viewModel = entry.sharedViewModel<ProfileViewModel>(navHostController)
                val state by viewModel.userTagList.collectAsStateWithLifecycle()
                ProfileScreen(navHostController, onNavigate = {
                    viewModel.updateUserTagList(listOf(             //TODO: переписать под получение списка из БД
                        "Спорт",
                        "Саморазвитие",
                        "Фильмы",
                        "IT",
                        "Автомобили"
                    ))
                    navHostController.navigate(ProfileItem.Tags.route)
                },
                    tags = state)
            }
            composable(
                route = ProfileItem.Tags.route
            ){entry ->
                val viewModel = entry.sharedViewModel<ProfileViewModel>(navHostController)
                val state by viewModel.userTagList.collectAsStateWithLifecycle()
                TagScreen(navHostController, state)
            }
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
        composable("toProfile") {entry ->
            val viewModel = entry.sharedViewModel<ProfileViewModel>(navHostController)
            val state by viewModel.userTagList.collectAsStateWithLifecycle()
            ProfileScreen(navHostController, onNavigate = {
                viewModel.updateUserTagList(listOf(             //TODO: переписать под получение списка из БД
                    "Спорт",
                    "Саморазвитие",
                    "Фильмы",
                    "IT",
                    "Автомобили"
                ))
                navHostController.navigate(ProfileItem.Tags.route)
            },
                tags = state)
        }
        composable(
            route = ProfileItem.Tags.route
        ){entry ->
            val viewModel = entry.sharedViewModel<ProfileViewModel>(navHostController)
            val state by viewModel.userTagList.collectAsStateWithLifecycle()
            TagScreen(navHostController, state)
        }
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
    }

}