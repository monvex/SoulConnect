package com.example.soulconnect.screens.start

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.soulconnect.navigation.BottomItem
import com.example.soulconnect.navigation.BottomNavigation
import com.example.soulconnect.navigation.NavGraph
import com.example.soulconnect.startpage.StartPage

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StartScreen(
    onNavigateToLogIn: () -> Unit,
    onNavigate: () -> Unit
) {
    StartPage(
        onLogInTapped = {
            onNavigateToLogIn()
        },
        onSignUpTapped = {
            onNavigate()
        }
    )
}