package br.com.geocdias.radiolacompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.geocdias.radiolacompose.R
import br.com.geocdias.radiolacompose.data.database.entities.SongEntity
import br.com.geocdias.radiolacompose.model.Song
import br.com.geocdias.radiolacompose.ui.theme.Black
import br.com.geocdias.radiolacompose.ui.theme.RadiolaComposeTheme
import coil.compose.AsyncImage

@Composable
fun PlayerComponent(
    modifier: Modifier = Modifier,
    song: Song
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Black)
    ) {
        AsyncImage(
            modifier = Modifier
                .width(70.dp)
                .height(70.dp),
            model = song.imageUrl,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.ic_launcher_background)
        )
        Text(
            text = song.title,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(horizontal = 16.dp)
                .weight(1f)
        )
        IconButton(
            modifier = Modifier
                .align(Alignment.CenterVertically),
            onClick = { /*TODO*/ }
        ) {
            Icon(
                modifier = Modifier.width(40.dp).height(40.dp).padding(end = 8.dp),
                imageVector = Icons.Filled.PlayArrow,
                contentDescription = "Play",
                tint = Color.White
            )
        }
    }
}

@Preview
@Composable
fun PlayerComponentPreview() {
    RadiolaComposeTheme {
        Surface {
            PlayerComponent(song = Song(title = "Song", imageUrl = ""))
        }
    }
}
