package com.example.soulconnect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import com.example.soulconnect.startpage.StartPage
import com.example.soulconnect.ui.theme.SoulConnectTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SoulConnectTheme {
                Column(
                    modifier = Modifier
                ) {
                    StartPage(
                        logInText = "Вход",
                        signUpText = "Регистрация"
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