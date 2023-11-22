package com.example.soulconnect

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(navController: NavController) {
    Text(text = "Поиск")
}

@Preview
@Composable
fun Test() {
    Column {
        Button(onClick = { /*TODO*/ }) {
            
        }
    }
}