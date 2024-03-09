package com.ruben.spotify.example.android

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun CardItem(
    modifier: Modifier = Modifier,
    image: String?,
    contentDescription: String,
    title: String
) {
    Card(
        modifier = modifier
            .height(140.dp)
            .width(100.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterHorizontally)
        ) {
            AsyncImage(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.play_circle),
                contentDescription = contentDescription,
                contentScale = ContentScale.FillBounds
            )

            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 8.dp)
                    .align(Alignment.CenterHorizontally),
                text = title,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}