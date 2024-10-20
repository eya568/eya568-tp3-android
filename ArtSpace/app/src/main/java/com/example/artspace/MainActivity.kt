package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtGallery()
                }
            }
        }
    }
}

@Composable
fun ArtGallery(modifier: Modifier = Modifier) {
    data class Artwork(
        val id: Int,
        val image: Int,
        val title: Int,
        val artist: Int,
        val description: Int
    )

    val artworks = listOf(
        Artwork(1, R.drawable.art, R.string.artwork_title1, R.string.artis1, R.string.artwork_desc1),
        Artwork(2, R.drawable.art1, R.string.artwork_title2, R.string.artis2, R.string.artwork_desc2),
        Artwork(3, R.drawable.art2, R.string.artwork_title3, R.string.artis3, R.string.artwork_desc3)
    )
    val currentIndex = remember { mutableStateOf(0) }
    val currentArtwork = artworks[currentIndex.value]

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(currentArtwork.image),
                contentDescription = stringResource(id = currentArtwork.title),
                modifier = Modifier.padding(10.dp)
            )
        }

        Column(modifier = Modifier.padding(10.dp).height(100.dp)) {
            Text(
                text = stringResource(id = currentArtwork.title),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Cursive,
                fontSize = 30.sp
            )
            Text(
                fontFamily = FontFamily.Cursive,
                text = stringResource(id = currentArtwork.artist)
            )
        }


        Card(modifier = Modifier.padding(20.dp)) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = stringResource(id = currentArtwork.description),
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.SansSerif
                )
                Text(
                    text = stringResource(id = currentArtwork.artist),
                    modifier = Modifier.padding(top = 8.dp),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Row(
            modifier = Modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = {
                    if (currentIndex.value == 0) {
                        currentIndex.value = artworks.size - 1
                    } else {
                        currentIndex.value--
                    }
                }
            ) {
                Text("Previous")
            }

            Button(
                onClick = {
                    if (currentIndex.value == artworks.size - 1) {
                        currentIndex.value = 0
                    } else {
                        currentIndex.value++
                    }
                }
            ) {
                Text("Next")
            }
        }
    }
}





@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
      ArtGallery()
    }
}