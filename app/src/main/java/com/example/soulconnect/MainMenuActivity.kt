package com.example.soulconnect

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import com.example.soulconnect.navigation.BottomNavigation
import com.example.soulconnect.navigation.NavGraph
import com.example.soulconnect.ui.theme.SoulConnectTheme

class MainMenuActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SoulConnectTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomNavigation(navController = navController)
                    }
                ) {
                    NavGraph(navHostController = navController)
                }
                }
            }
        }
    }
