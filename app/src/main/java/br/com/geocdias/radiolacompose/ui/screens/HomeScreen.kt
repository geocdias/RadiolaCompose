package br.com.geocdias.radiolacompose.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.geocdias.radiolacompose.data.Song
import br.com.geocdias.radiolacompose.samples.sampleSongs
import br.com.geocdias.radiolacompose.ui.components.SongComponent
import br.com.geocdias.radiolacompose.ui.theme.RadiolaComposeTheme

@Composable
fun HomeScreen() {
    HomeScreen(sampleSongs)
}


@Composable
private fun HomeScreen(
    songs: List<Song>
) {
    Column(Modifier.fillMaxSize()) {
        Text(
            text = "Músicas",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            fontSize = 16.sp
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(songs) { song ->
                SongComponent(
                    song = song,
                    onClick = { id ->
                        println("Song id $id")
                    }
                )
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    RadiolaComposeTheme {
        Surface {
            HomeScreen(sampleSongs)
        }
    }
}
