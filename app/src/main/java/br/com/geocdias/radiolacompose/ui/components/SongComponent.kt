package br.com.geocdias.radiolacompose.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.geocdias.radiolacompose.R
import br.com.geocdias.radiolacompose.data.Song
import br.com.geocdias.radiolacompose.ui.theme.RadiolaComposeTheme
import coil.compose.AsyncImage

@Composable
fun SongComponent(
    song: Song,
    onClick: (id: String) -> Unit = {}
) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable {
                onClick(song.id)
            }
    ) {
        AsyncImage(
            model = song.imageUrl,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.ic_launcher_background)
        )
        Text(
            text = song.title,
            modifier = Modifier.align(Alignment.CenterVertically).padding(horizontal = 8.dp)
        )
    }
}

@Preview
@Composable
fun SongComponentPreview() {
    RadiolaComposeTheme {
        Surface {
            SongComponent(Song("1", "Song", ""))
        }
    }
}
