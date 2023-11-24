package com.example.soulconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.soulconnect.navigation.NavGraph
import com.example.soulconnect.startpage.StartPage
import com.example.soulconnect.ui.theme.SoulConnectTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SoulConnectTheme {
                val navController = rememberNavController()
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    NavGraph(navHostController = navController)
                    StartPage(
                        onLogInTapped = {
                            navController.navigate("toLogIn")
                        },
                        onSignUpTapped = {
                            val navigate = Intent(this@MainActivity, MainMenuActivity::class.java)
                            startActivity(navigate)
                        }
                        )
                }
            }
        }
    }


    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}