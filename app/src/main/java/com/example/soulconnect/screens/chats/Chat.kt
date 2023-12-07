package com.example.soulconnect.screens.chats


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.soulconnect.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Chat(chatId: Int? = null, picId: String?, name: String?, text: String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp
    ) {
        Box(
        )
        {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                GlideImage(
                    model = picId,
                    contentDescription = "interlocutorPhoto",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(5.dp)
                        .size(64.dp)
                        .clip(CircleShape)
                )
                Column() {
                    Text(
                        text = name ?: "Анлак",
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.relay_comfortaa_regular, weight = FontWeight.W400, style = FontStyle.Normal))
                    )
                    Text(
                        fontFamily = FontFamily(Font(R.font.relay_comfortaa_regular, weight = FontWeight.W400, style = FontStyle.Normal)) ,
                        text = text
                    )
                }
            }
        }
    }
}