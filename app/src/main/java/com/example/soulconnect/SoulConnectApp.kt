package com.example.soulconnect

import android.annotation.SuppressLint
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.soulconnect.navigation.BottomItem
import com.example.soulconnect.navigation.BottomNavigation
import com.example.soulconnect.navigation.NavGraph
import com.example.soulconnect.ui.theme.SoulConnectTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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
        val navController = rememberNavController()
        val showBottomBar = navController
            .currentBackStackEntryAsState().value?.destination?.route in listItems.map { it.route }

        Scaffold(
            bottomBar = {
                if(showBottomBar) {
                    BottomNavigation(navController = navController)
                }
            }
        ) {
            NavGraph(navHostController = navController)
        }

    }
}