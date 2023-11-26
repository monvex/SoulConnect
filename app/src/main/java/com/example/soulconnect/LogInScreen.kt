package com.example.soulconnect

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.soulconnect.startpage.TopLevel
import com.example.soulconnect.startpage.comfortaa
import com.google.relay.compose.BoxScopeInstance.boxAlign
import com.google.relay.compose.RelayContainer
import com.google.relay.compose.RelayContainerArrangement
import com.google.relay.compose.RelayContainerScope
import com.google.relay.compose.RelayImage
import com.google.relay.compose.RelayText
import com.google.relay.compose.relayDropShadow
import com.google.relay.compose.tappable

@Composable
fun LogInScreen(onNavigate: () -> Unit)
{
    TopLevel(modifier = Modifier) {
        Background()
        Logo(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopCenter,
                offset = DpOffset(
                    x = -5.142856597900391.dp,
                    y = 87.0.dp
                )
            )
        )
        var loginText by remember { mutableStateOf("") }
        var passwordText by remember { mutableStateOf("") }
        Column(modifier = Modifier.boxAlign(
            alignment = Alignment.TopCenter,
            offset = DpOffset(
                x = 0.dp,
                y = 280.dp
            )
        )){
            Text(
                text = "Логин:",
                fontFamily = FontFamily(
                    Font(
                        R.font.relay_comfortaa_regular,
                        weight = FontWeight.W400,
                        style = FontStyle.Normal
                    )
                ),
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            OutlinedTextField(
                value = loginText,
                onValueChange = {loginText = it},
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.7f),
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp,
                )
            )
            Text(
                text = "Пароль:",
                fontFamily = FontFamily(
                    Font(
                        R.font.relay_comfortaa_regular,
                        weight = FontWeight.W400,
                        style = FontStyle.Normal
                    )
                ),
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            OutlinedTextField(
                value = passwordText,
                onValueChange = {passwordText = it},
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.7f),
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp,
                )
            )
        }
    }
    RelayContainer(
        backgroundColor = Color(
            alpha = 255,
            red = 255,
            green = 255,
            blue = 255
        ),
        arrangement = RelayContainerArrangement.Row,
        padding = PaddingValues(
            start = 19.0.dp,
            top = 9.0.dp,
            end = 19.0.dp,
            bottom = 9.0.dp
        ),
        itemSpacing = 10.0,
        clipToParent = false,
        radius = 100.0,
        content = {Entry()},
        modifier = Modifier
            .tappable(onTap = {checkUserData(loginText, loginPassword)})
            .boxAlign(
                alignment = Alignment.TopCenter,
                offset = DpOffset(
                    x = -0.5.dp,
                    y = 470.0.dp
                )
            )
            .fillMaxWidth(0.5f)
            .relayDropShadow(
                color = Color(
                    alpha = 63,
                    red = 0,
                    green = 0,
                    blue = 0
                ),
                borderRadius = 100.0.dp,
                blur = 4.0.dp,
                offsetX = 0.0.dp,
                offsetY = 4.0.dp,
                spread = 0.0.dp
            )
    )
}


@Composable
fun Background(modifier: Modifier = Modifier) {
    RelayImage(
        image = painterResource(R.drawable.start_page_background),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .requiredWidth(1680.0.dp)
            .requiredHeight(1108.0.dp)
    )
}

@Composable
fun Logo(modifier: Modifier = Modifier) {
    RelayImage(
        image = painterResource(R.drawable.start_page_logo),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .requiredWidth(85.71428680419922.dp)
            .requiredHeight(120.0.dp)
    )
}

@Composable
fun Entry(modifier: Modifier = Modifier) {
    RelayText(
        content = "Вход",
        fontSize = 20.0.sp,
        fontFamily = comfortaa,
        color = Color(
            alpha = 255,
            red = 0,
            green = 0,
            blue = 0
        ),
        height = 1.1149999618530273.em,
        textAlign = TextAlign.Left,
        modifier = modifier
    )
}