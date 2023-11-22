package com.example.soulconnect.navigation

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
@Composable
fun BottomNavigation(
    navController: NavController
) {
    val listItems = listOf(
        BottomItem.Search,
        BottomItem.Chats,
        BottomItem.GroupChats,
        BottomItem.Profile
    )
    androidx.compose.material.BottomNavigation(
        modifier = Modifier
            .fillMaxHeight(0.05f),
        backgroundColor = Color.White


    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        listItems.forEach {item->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                onClick = { navController.navigate(item.route) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconId),
                        contentDescription = "Icon"
                    )
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Gray,
                interactionSource = NoRippleInteractSource()

            )
        }
    }
}