package net.idrok.muhandislik

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.NavType.Companion.IntType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlinx.coroutines.delay

import net.idrok.muhandislik.ui.theme.MuhandislikQollanmaTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MuhandislikQollanmaTheme {
                val navController = rememberNavController()
                NavHost(navController, "loading") {
                    composable("loading") {
                        Loading {
                            navController.navigate("main") {
                                popUpTo("loading") { inclusive = true }
                            }
                        }
                    }
                    composable("main") {
                        MainScreen(
                            onB = {
                                navController.navigate("articlesB") {
                                    popUpTo("loading") { inclusive = true
                                        savedInstanceState?.clear()}
                                }
                            },
                            onF = {
                                navController.navigate("articlesF") {
                                    popUpTo("loading") { inclusive = true
                                        savedInstanceState?.clear()}

                                }
                            },
                            onH = {
                                navController.navigate("articlesH") {
                                    popUpTo("loading") { inclusive = true
                                        savedInstanceState?.clear()}
                                }
                            },
                            onV = {
                                navController.navigate("articlesV") {
                                    popUpTo("loading") { inclusive = true
                                        savedInstanceState?.clear()}
                                }
                            }
                        )
                    }
                    composable("articlesF") {
                        ArticleListScreen(articlesF, "Football", onArticleSelect = { article ->
                            navController.navigate("articleF/$article") {
                                popUpTo("articlesF") {
                                 //   inclusive = true
                                //savedInstanceState?.clear()
                                }
                            }
                        })
                    }
                    composable(
                        "articleF/{article}",
                        arguments = listOf(navArgument("article") { type = IntType })
                    ) {
                        val article = it.arguments?.getInt("article") ?: 0
                        val articleName = articlesF[article].id
                        ArticleScreen(articlesF[articleName ], onHome = {
                            navController.navigate("main") {
                                popUpTo("articleF/$article") {
                                    inclusive = true
                                    //savedInstanceState?.clear()
                                }
                            }
                        })
                    }
                    composable("articlesB") {
                        ArticleListScreen(articlesB, "Basketball", onArticleSelect = { article ->
                            navController.navigate("articleB/$article") {
                                popUpTo("articlesB") {
                                 //   inclusive = true
                                //    savedInstanceState?.clear()
                                }
                            }
                        })
                    }
                    composable(
                        "articleB/{article}",
                        arguments = listOf(navArgument("article") { type = IntType })
                    ) {
                        val article = it.arguments?.getInt("article") ?: 0
                        val articleName = articlesB[article].id
                        ArticleScreen(articlesB[articleName ], onHome = {
                            navController.navigate("main") {
                                popUpTo("articleB/$article") {
                                    inclusive = true
                                   // savedInstanceState?.clear()
                                }
                            }
                        })
                    }
                    composable("articlesV") {
                        ArticleListScreen(articlesV, "Volleyball", onArticleSelect = { article ->
                            navController.navigate("articleV/$article") {
                                popUpTo("articlesV") {
                                    //inclusive = true
                                 //   savedInstanceState?.clear()
                                }
                            }
                        })
                    }
                    composable("articlesH") {
                        ArticleListScreen(articlesH, "Hockey", onArticleSelect = { article ->
                            navController.navigate("articleH/$article") {
                                popUpTo("articlesH") {
                                    //inclusive = true
                                  //  savedInstanceState?.clear()
                                }
                            }
                        })
                    }
                    composable(
                        "articleH/{article}",
                        arguments = listOf(navArgument("article") { type = IntType })
                    ) {
                        val article = it.arguments?.getInt("article") ?: 0
                        val articleName = articlesH[article].id
                        ArticleScreen(articlesH[articleName ], onHome = {
                            navController.navigate("main") {
                                popUpTo("articleH/$article") {
                                    inclusive = true
                                   // savedInstanceState?.clear()
                                }
                            }
                        })
                    }
                    composable(
                        "articleV/{article}",
                        arguments = listOf(navArgument("article") { type = IntType })
                    ) {
                        val article = it.arguments?.getInt("article") ?: 0
                        val articleName = articlesV[article].id
                        ArticleScreen(articlesV[articleName ], onHome = {
                            navController.navigate("main") {
                                popUpTo("articleV/$article") {
                                    inclusive = true
                                   // savedInstanceState?.clear()
                                }
                            }
                        })
                    }


                }
            }
        }
    }
}


@Composable
fun Loading(
    onNext: () -> Unit = {}
) {
    LaunchedEffect(Unit) {
        delay(1000)
        onNext()
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)

    )
    {
        CircularProgressIndicator(
            color = Color.Blue
        )
    }

}


@Composable
fun MainScreen(onF: () -> Unit, onB: () -> Unit, onV: () -> Unit, onH: () -> Unit) {
    BackHandler {
        // Пустое тело, что запрещает закрытие приложения по кнопке "Назад"
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {



            Column(horizontalAlignment = Alignment.CenterHorizontally) {


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
                    imageRes = R.drawable.basket,
                    title = "Basketball",
                    onClick = { onB() }
                )
                ImageWithTitle(
                    imageRes = R.drawable.football,
                    title = "Football",
                    onClick = { onF() }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ImageWithTitle(
                    imageRes = R.drawable.hokkei,
                    title = "Hockey",
                    onClick = { onH() }
                )
                ImageWithTitle(
                    imageRes = R.drawable.voleiball,
                    title = "Volleyball",
                    onClick = { onV() }
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

