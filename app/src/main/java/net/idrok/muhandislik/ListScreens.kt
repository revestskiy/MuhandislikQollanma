package net.idrok.muhandislik

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp




@Composable
fun ArticleListScreen(arr: List<Article>,title:String ,onArticleSelect:(Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        TopBar(title)



        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(arr) { article ->
                ArticleItem(article = article, onClick = {onArticleSelect(article.id)

                })
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun TopBar(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Иконка меню слева
        Image(
            painter = painterResource(id = R.drawable.menu_svgrepo_com), // Замените на свою иконку меню
            contentDescription = "Menu",
            modifier = Modifier
                .size(44.dp)
                .clickable {
                    // Действие при нажатии на иконку меню
                }
        )
        Spacer(modifier = Modifier.width(16.dp))

        // Заголовок рядом с иконкой меню
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
fun ArticleItem(article: Article, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = article.imageRes),
            contentDescription = article.title,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(220.dp)
                .padding(end = 16.dp)
        )
        Text(
            text = article.title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
data class Article(val id: Int, val title: String, val imageRes: Int, val description: String)

