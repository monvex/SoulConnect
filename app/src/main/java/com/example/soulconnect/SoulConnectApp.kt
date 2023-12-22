package com.example.soulconnect

import android.annotation.SuppressLint
import android.content.res.Resources
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.soulconnect.common.snackbar.SnackbarManager
import com.example.soulconnect.navigation.BottomItem
import com.example.soulconnect.navigation.BottomNavigation
import com.example.soulconnect.navigation.NavGraph
import com.example.soulconnect.ui.theme.SoulConnectTheme
import kotlinx.coroutines.CoroutineScope

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
@ExperimentalMaterialApi
fun SoulConnectApp(){
    SoulConnectTheme {
        val listItems = listOf(
            BottomItem.Search,
            BottomItem.Chats,
            BottomItem.GroupChats,
            BottomItem.Profile
        )
        val appState = rememberAppState()
        val showBottomBar = appState.navController.currentBackStackEntryAsState().value?.destination?.route in listItems.map { it.route }

        Surface(color = MaterialTheme.colorScheme.background) {

            Scaffold(
                bottomBar = {
                    if(showBottomBar) {
                        BottomNavigation(navController = appState.navController)
                    }
                },
                scaffoldState = appState.scaffoldState
            ) {
                NavGraph(appState)
            }
        }

    }

}


@Composable
fun rememberAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) =
    remember(scaffoldState, navController, snackbarManager, resources, coroutineScope) {
        SoulConnectAppState(scaffoldState, navController, snackbarManager, resources, coroutineScope)
    }

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}