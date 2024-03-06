package com.ruben.spotify.example.android.featuredPlaylists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ruben.spotify.api.response.Image
import com.ruben.spotify.api.response.Playlist
import com.ruben.spotify.example.android.CardItem
import com.ruben.spotify.example.android.R
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import org.koin.androidx.compose.koinViewModel

@Composable
fun FeaturedPlaylists(
    modifier: Modifier = Modifier,
    featuredPlaylistsViewModel: FeaturedPlaylistsViewModel = koinViewModel()
) {
    val uiState by featuredPlaylistsViewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(id = R.string.featured_playlists_title),
            fontWeight = FontWeight.Bold
        )

        if (uiState is FeaturedPlaylistsState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.CenterHorizontally)
            )
        } else {
            (uiState as? FeaturedPlaylistsState.Result)?.let { result ->
                result.playlists?.let {
                    Playlists(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        playlists = it
                    )
                }
            }
        }
    }
}

@Composable
private fun Playlists(
    modifier: Modifier = Modifier,
    playlists: ImmutableList<Playlist>
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = playlists,
            key =  { item: Playlist -> item.id }
        ) {
            PlaylistItem(it)
        }
    }
}

@Composable
private fun PlaylistItem(
    item: Playlist
) {
    CardItem(
        image = item.images.firstOrNull { it.url.isNotEmpty() }?.url,
        contentDescription = stringResource(id = R.string.content_description_playlist),
        title = item.name
    )
}

@Preview
@Composable
private fun PreviewPlaylistItem() {
    PlaylistItem(
        item = Playlist(
            collaborative = false,
            id = "1234",
            public = true,
            name = "Top of the charts",
            description = "My description",
            images = listOf(
                Image(height = 100, width = 100, url = "https://myimage.png")
            )
        )
    )
}

@Preview
@Composable
private fun PreviewPlaylists() {
    Playlists(
        playlists = listOf(
            Playlist(
                collaborative = false,
                id = "1234",
                public = true,
                name = "Top of the charts",
                description = "My description1",
                images = listOf(
                    Image(height = 100, width = 100, url = "https://myimage1.png")
                )
            ),
            Playlist(
                collaborative = false,
                id = "5678",
                public = true,
                name = "Top hits",
                description = "My description2",
                images = listOf(
                    Image(height = 100, width = 100, url = "https://myimage2.png")
                )
            )
        ).toImmutableList()
    )
}