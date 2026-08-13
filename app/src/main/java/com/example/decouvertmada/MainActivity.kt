package com.example.decouvertmada

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Destination(
    val lieu: String,
    val region: String,
    val description: String,
    val activity: String,
    @DrawableRes val imageRes: Int
)

// Rasoloarimanana Mitia Dylan L2 N 36
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DecouverteMadaScreen()
                }
            }
        }
    }
}

@Composable
fun DecouverteMadaScreen() {
    val destinations = listOf(
        Destination(
            lieu = "Mahajanga",
            region = "Boeny",
            description = "La Cité des Fleurs et son baobab géant.",
            activity = "Balade & Découverte",
            imageRes = R.drawable.mahajanga
        ),
        Destination(
            lieu = "Morondava",
            region = "Menabe",
            description = "La mythique Allée des Baobabs de Morondava.",
            activity = "Photos & Coucher de soleil",
            imageRes = R.drawable.morondava
        ),
        Destination(
            lieu = "Nosy Be",
            region = "DIANA",
            description = "Île paradisiaque bordée de plages.",
            activity = "Plongée & Baignade",
            imageRes = R.drawable.nosybe
        ),
        Destination(
            lieu = "Sainte Marie",
            region = "Analanjirofo",
            description = "Île tropicale authentique.",
            activity = "Observation des baleines",
            imageRes = R.drawable.saintemarie
        ),
        Destination(
            lieu = "Tamatave",
            region = "Atsinanana",
            description = "Le grand port de l'Est et son bord de mer.",
            activity = "Promenade & Détente",
            imageRes = R.drawable.tamatave
        ),
        Destination(
            lieu = "Antsirabe",
            region = "Vakinankaratra",
            description = "La ville d'eau et sa gare historique.",
            activity = "Visite historique & Thermes",
            imageRes = R.drawable.antsirabe
        )
    )

    val likesMap = remember { mutableStateMapOf<Int, Int>() }
    val dislikesMap = remember { mutableStateMapOf<Int, Int>() }

    val totalLikes = likesMap.values.sum()
    val totalDislikes = dislikesMap.values.sum()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF00695C))
                .padding(top = 48.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "titre : Découverte Mada",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "👍 $totalLikes",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF81C784)
                )
                Text(
                    text = "👎 $totalDislikes",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFE57373)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsIndexed(destinations) { index, destination ->
                val likeCount = likesMap[index] ?: 0
                val dislikeCount = dislikesMap[index] ?: 0

                DestinationCard(
                    destination = destination,
                    likeCount = likeCount,
                    dislikeCount = dislikeCount,
                    onLikeClick = {
                        likesMap[index] = (likesMap[index] ?: 0) + 1
                    },
                    onDislikeClick = {
                        dislikesMap[index] = (dislikesMap[index] ?: 0) + 1
                    }
                )
            }
        }
    }
}

@Composable
fun DestinationCard(
    destination: Destination,
    likeCount: Int,
    dislikeCount: Int,
    onLikeClick: () -> Unit,
    onDislikeClick: () -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = destination.imageRes),
                contentDescription = destination.lieu,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.85f)
                            )
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = destination.lieu,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White
                )
                Text(
                    text = "Région : ${destination.region}",
                    fontSize = 12.sp,
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = destination.description,
                    fontSize = 11.sp,
                    color = Color.White.copy(alpha = 0.9f),
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Activité : ${destination.activity}",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFFFFD700)
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onLikeClick,
                        modifier = Modifier.size(40.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(text = "👍", fontSize = 16.sp)
                            if (likeCount > 0) {
                                Spacer(modifier = Modifier.width(2.dp))
                                Text(
                                    text = "$likeCount",
                                    fontSize = 11.sp,
                                    color = Color.Green,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.width(4.dp))

                    IconButton(
                        onClick = onDislikeClick,
                        modifier = Modifier.size(40.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(text = "👎", fontSize = 16.sp)
                            if (dislikeCount > 0) {
                                Spacer(modifier = Modifier.width(2.dp))
                                Text(
                                    text = "$dislikeCount",
                                    fontSize = 11.sp,
                                    color = Color.Red,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DecouverteMadaPreview() {
    MaterialTheme {
        DecouverteMadaScreen()
    }
}