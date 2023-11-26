package com.example.soulconnect

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.soulconnect.navigation.BottomItem
import com.example.soulconnect.navigation.BottomNavigation
import com.example.soulconnect.navigation.NavGraph
import com.example.soulconnect.startpage.StartPage
import com.example.soulconnect.ui.theme.SoulConnectTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class MainActivity : AppCompatActivity() {

    fun checkUserData(login: String, password: String) {
        lateinit var auth: FirebaseAuth;
        auth = Firebase.auth
        auth.signInWithEmailAndPassword(login, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                }
                else {
                }
            }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lateinit var auth: FirebaseAuth;
        auth = Firebase.auth
        val currentUser = auth.currentUser
        setContent {
            SoulConnectTheme {
//                val navController = rememberNavController()
//                Column(
//                    modifier = Modifier.fillMaxSize()
//                ) {
//                    NavGraph(navHostController = navController)
//                    StartPage(
//                        onLogInTapped = {
//                            navController.navigate("toLogIn")
//                        },
//                        onSignUpTapped = {
//                            navController.navigate()
//                        }
//                        )
//                }
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
                    NavGraph(navHostController = navController, currentUser)
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