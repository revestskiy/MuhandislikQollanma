package com.skyflygame.net.idrok.muhandislik

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


@Preview
@Composable
fun ArticleScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        TopBarWithHome()


        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.foot1), // Замените на свою картинку
            contentDescription = "Article Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "The FIFA World Cup is the premier tournament in world football, held every four years since 1930. This article will look at its rich history and influence on the global culture of sports.\n" +
                    "\n" +
                    "The main stages of the tournament's development:\n" +
                    "\n" +
                    "The first World Cup was held in 1930 in Uruguay, where only 13 teams took part. This tournament became a landmark event that laid the foundations for international football competition.\n" +
                    "\n" +
                    "Over the years, the tournament grew, and by 1954, 16 teams took part. In the post-war period, the championship became an important symbol of unity and reconstruction.\n" +
                    "\n" +
                    "The 1970s and 1980s saw the emergence of football stars such as Pele and Maradona, who left a bright mark on the history of the tournament.\n" +
                    "\n" +
                    "Iconic moments and matches:\n" +
                    "\n" +
                    "The 1950 World Cup, which was held in Brazil, was remembered for the Maracanazo, a historic defeat of the Brazilian national team to Uruguay on home soil.\n" +
                    "England's victory in 1966 on home soil remains the most important achievement for English football.\n" +
                    "In 1986, Diego Maradona gave the world the \"hand of God\" and the best goal of all time against England.\n" +
                    "\n" +
                    "Social and political influence:\n" +
                    "\n" +
                    "Tournaments often reflect the political situation in the world. For example, the World Cup in 1938 and 1942 was not held due to World War II.\n" +
                    "In 1978, Argentina won the tournament on home soil, which was an important event against the backdrop of political turmoil in the country.\n" +
                    "The World Cup has come a long way from a local competition to a global show, where technology, broadcasting and new formats have made it one of the most anticipated events in the world of sport.\n",
            fontSize = 18.sp,
            color = Color.Black
        )
    }
}

@Composable
fun TopBarWithHome() {
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
                    // Действие при нажатии на иконку дом
                }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Image(
            painter = painterResource(id = R.drawable.menu_svgrepo_com), // Замените на свою иконку меню
            contentDescription = "Menu",
            modifier = Modifier
                .size(34.dp)
                .clickable {

                }
        )
        Spacer(modifier = Modifier.width(16.dp))

        // Заголовок
        Text(
            text = "History of the FIFA World Cup",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )
    }
}
