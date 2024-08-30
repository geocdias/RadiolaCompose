package br.com.geocdias.radiolacompose.ui.screens.player

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.geocdias.radiolacompose.R
import br.com.geocdias.radiolacompose.model.Song
import br.com.geocdias.radiolacompose.samples.sampleSongs
import br.com.geocdias.radiolacompose.ui.theme.LightBackground
import br.com.geocdias.radiolacompose.ui.theme.MediumDarkBackground
import br.com.geocdias.radiolacompose.ui.theme.RadiolaComposeTheme
import br.com.geocdias.radiolacompose.ui.theme.Red_500
import coil.compose.AsyncImage
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PlayerScreen(
    songId: String,
) {
    val song = sampleSongs.first { it.title == songId }
    var currentProgress by remember { mutableFloatStateOf(0f) }
    var isPlaying by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    PlayerContent(
        song = song,
        currentProgress = currentProgress,
        isPlaying = isPlaying,
        onPlayPause = {
            isPlaying = !isPlaying
            if (isPlaying) {
                println("isPlaying $isPlaying")

                scope.launch {
                    println("scope")
                    loadProgress { progress ->
                        currentProgress = progress
                    }
                }
            } else {
                scope.cancel()
                println("isPlaying $isPlaying")
            }

        }
    )
}

private suspend fun loadProgress(updateProgress: (Float) -> Unit) {
    println("loadProgress")
    for (i in 0..100) {
        println("Progress ${i.toFloat() / 100}")
        updateProgress(i.toFloat() / 100)
        delay(100)
    }
}

@Composable
private fun PlayerContent(
    song: Song,
    currentProgress: Float = 1f,
    isPlaying: Boolean = false,
    onPlayPause: () -> Unit = {},
    onForward: () -> Unit = {},
    onBackward: () -> Unit = {},
) {

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MediumDarkBackground),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        AsyncImage(
            modifier = Modifier
                .width(300.dp)
                .height(300.dp),
            model = song.imageUrl,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.ic_launcher_background)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = song.title,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(64.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {
            Text(
                text = "00:00",
                color = Color.White,
                fontSize = 14.sp
            )
            LinearProgressIndicator(
                modifier = Modifier.align(Alignment.CenterVertically),
                progress = { currentProgress },
                color = Color.White,
                trackColor = LightBackground
            )
            Text(
                text = "03:20",
                color = Color.White,
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.height(64.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {
            IconButton(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    modifier = Modifier
                        .width(60.dp)
                        .height(60.dp)
                        .padding(end = 8.dp),
                    painter = painterResource(id = R.drawable.ic_skip_previous),
                    contentDescription = "Play",
                    tint = Color.White
                )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .background(Red_500, shape = CircleShape)
                    .clickable {
                        onPlayPause()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .width(60.dp)
                        .height(60.dp),
                    painter = painterResource(
                        id = if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play
                    ),
                    contentDescription = "Play",
                    tint = Color.White
                )
            }
            IconButton(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    modifier = Modifier
                        .width(60.dp)
                        .height(60.dp)
                        .padding(end = 8.dp),
                    painter = painterResource(id = R.drawable.ic_skip),
                    contentDescription = "Play",
                    tint = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun PlayerScreenPreview() {
    RadiolaComposeTheme {
        PlayerContent(song = Song(
            title = "Song",
            imageUrl = "https://picsum.photos/200"
        )
        )
    }
}
