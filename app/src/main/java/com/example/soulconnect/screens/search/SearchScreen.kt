package com.example.soulconnect.screens.search

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.soulconnect.R
import com.example.soulconnect.common.snackbar.SnackbarManager
import com.example.soulconnect.model.User
import com.example.soulconnect.text_functions.AutoResizedText
import org.checkerframework.checker.signature.qual.PrimitiveType
import kotlin.math.abs

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    SearchScreenContent(
        uiState,
        update = {
            viewModel.updateCandidate()
        }
    )
}



@Composable
fun SearchScreenContent(
    uiState: SearchUiState,
    update: () -> Unit
) {

    Image(
        painter = painterResource(id = R.drawable.start_page_background),
        contentDescription = "background",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Фотка плюс описание поверх фото, потом слайдер добавить
        var direction by remember { mutableStateOf(-1) }
        Box(modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .fillMaxHeight(0.8f)
            .fillMaxWidth(0.95f)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { change, dragAmount ->
                        change.consume()
                        val (x, y) = dragAmount
                        if (abs(x) > abs(y)) {
                            when {
                                x > 0 -> {
                                    //right
                                    direction = 0
                                }

                                x < 0 -> {
                                    // left
                                    direction = 1
                                }
                            }
                        }
                    },
                    onDragEnd = {
                        when (direction) {
                            0 -> {
                                update()
                            }

                            1 -> {
                                update()
                            }
                        }
                    })
            },
                contentAlignment = Alignment.TopCenter
                )

        {
            // Фотография
            /* TODO: Фотография кандидата */
            Box(modifier = Modifier
                .fillMaxSize()
            ) {
                Image(painter = painterResource(id = R.drawable.test_pic_1), contentDescription = "test", modifier = Modifier
                    .fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
            }

            Column(
            ) {
                // Город
                Box(modifier = Modifier
                    .padding(start = 20.dp, top = 10.dp)
                ) {
                    Text(
                        text = uiState.candidate?.city ?: "Анлак", /* TODO: заменить */
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
                }

                // Пустой контейнер
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
                )

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.25f)
                    .padding(start = 20.dp)

                ) {
                    Text(
                        text = "${uiState.candidate?.name}, ${uiState.candidate?.age}", /* TODO: заменить */
                        fontFamily = FontFamily(
                            Font(
                                R.font.relay_comfortaa_regular,
                                weight = FontWeight.W400,
                                style = FontStyle.Normal
                            )
                        ),
                        fontSize = 30.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                        )

                }


                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black.copy(alpha = 0.3f))
                    .padding(top = 2.dp, start = 10.dp, end = 10.dp)
                    .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = uiState.candidate?.description ?: "Анлак",
                        fontFamily = FontFamily(
                            Font(
                                R.font.relay_comfortaa_regular,
                                weight = FontWeight.W400,
                                style = FontStyle.Normal
                            )
                        ),
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }
                // Нижняя часть фотографии с описанием кандидата(1 фото - текстовое, второе тэги, дальше тэги)
                /* TODO: Блок с описанием */
            }





        }
    }
}