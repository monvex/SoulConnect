package com.example.soulconnect.screens.search

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen() {
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