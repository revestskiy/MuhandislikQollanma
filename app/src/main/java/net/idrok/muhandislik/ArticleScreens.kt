package net.idrok.muhandislik

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp




@Composable
fun ArticleScreen(article: Article, onHome: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        TopBarWithHome(article.title, onHome )


        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = article.imageRes), // Замените на свою картинку
            contentDescription = "Article Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = article.description,
            fontSize = 18.sp,
            color = Color.Black
        )
    }
}

@Composable
fun TopBarWithHome(title: String, onHome: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.home_svgrepo_com), // Замените на свою иконку дом
            contentDescription = "Home",
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    onHome()
                }
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Заголовок
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )
    }
}
