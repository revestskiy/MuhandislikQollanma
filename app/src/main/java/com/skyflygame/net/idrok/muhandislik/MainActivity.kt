package com.skyflygame.net.idrok.muhandislik

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skyflygame.net.idrok.muhandislik.ui.theme.MuhandislikQollanmaTheme
import androidx.compose.material3.Text // для Compose Material 3
import androidx.compose.ui.graphics.PaintingStyle.Companion.Fill
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Preview
@Composable
fun MainScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Image(
                painter = painterResource(id = R.drawable.menu_svgrepo_com), // Используйте свою иконку меню
                contentDescription = "Menu",
                modifier = Modifier
                    .size(44.dp)
                    .clickable {

                    }
            )


            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Muhandislik qo'llanma",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                )

                Text(
                    text = "Sport rahbariyati",
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            // Добавим пустой элемент справа, чтобы заголовок был по центру
            Spacer(modifier = Modifier.size(24.dp)) // это нужно, чтобы балансировать пустое место
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Карточки ниже
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ImageWithTitle(
                    imageRes = R.drawable.basket1,
                    title = "1 TEXT",
                    onClick = {}
                )
                ImageWithTitle(
                    imageRes = R.drawable.basket2,
                    title = "2 TEXT",
                    onClick = {}
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ImageWithTitle(
                    imageRes = R.drawable.basket3,
                    title = "3 TEXT",
                    onClick = {}
                )
                ImageWithTitle(
                    imageRes = R.drawable.basket4,
                    title = "4 TEXT",
                    onClick = {}
                )
            }
        }
    }
}

@Composable
fun ImageWithTitle(imageRes: Int, title: String, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .width(160.dp)
            .height(200.dp) // Увеличим высоту карточки, чтобы текст хорошо помещался
            .clickable { onClick() }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Добавляем текст под картинкой
            Text(
                text = title, // Используем заголовок для текста
                color = Color.Black,
                fontSize = 14.sp
            )
        }
    }
}

