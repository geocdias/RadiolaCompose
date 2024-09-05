package br.com.geocdias.radiolacompose.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.geocdias.radiolacompose.domain.model.Song
import br.com.geocdias.radiolacompose.samples.sampleSongs
import br.com.geocdias.radiolacompose.ui.components.PlayerComponent
import br.com.geocdias.radiolacompose.ui.components.SongComponent
import br.com.geocdias.radiolacompose.ui.theme.RadiolaComposeTheme
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    onClick: (String) -> Unit = {}
) {
    val viewModel: HomeViewModel = koinInject()
    val songsState by viewModel.songsFlow.collectAsState(HomeScreenUiState())
    viewModel.getAllSongs()

    HomeScreenContent(songsState, onClick)
}


@Composable
private fun HomeScreenContent(
    songsState: HomeScreenUiState = HomeScreenUiState(),
    onClick: (String) -> Unit = {}
) {
   val songList = songsState.songs
   if (songList.isEmpty()) return

   Box(modifier = Modifier.fillMaxSize()) {
       PlayList(songList, onClick)
       PlayerComponent(
           modifier = Modifier.align(Alignment.BottomCenter),
           songList.first()
       )
   }
}

@Composable
private fun PlayList(
    songs: List<Song>,
    onClick: (String) -> Unit
) {
    Column(
        Modifier.fillMaxSize()
    ) {
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
                    modifier = Modifier.padding(
                        bottom = 80.dp.takeIf { song.title == sampleSongs.last().title } ?: 0.dp
                   ),
                    song = song,
                    onClick = {
                        onClick(song.mediaId)
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(150.dp))

    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    RadiolaComposeTheme {
        Surface {
            HomeScreenContent(HomeScreenUiState(sampleSongs))
        }
    }
}
