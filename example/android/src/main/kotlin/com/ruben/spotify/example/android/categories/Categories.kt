package com.ruben.spotify.example.android.categories

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
import com.ruben.spotify.api.response.Category
import com.ruben.spotify.api.response.Image
import com.ruben.spotify.example.android.CardItem
import com.ruben.spotify.example.android.R
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import org.koin.androidx.compose.koinViewModel

@Composable
fun Categories(
    modifier: Modifier = Modifier,
    categoriesViewModel: CategoriesViewModel = koinViewModel()
) {
    val uiState by categoriesViewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(id = R.string.categories_title),
            fontWeight = FontWeight.Bold
        )

        if (uiState is CategoriesState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.CenterHorizontally)
            )
        } else {
            (uiState as? CategoriesState.Result)?.let { result ->
                result.categories?.let {
                    CategoriesContent(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        categories = it
                    )
                }
            }
        }
    }
}

@Composable
private fun CategoriesContent(
    modifier: Modifier = Modifier,
    categories: ImmutableList<Category>
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = categories,
            key = { item: Category -> item.id }
        ) {
            CategoryItem(it)
        }
    }
}

@Composable
private fun CategoryItem(
    category: Category
) {
    CardItem(
        image = category.image.firstOrNull { it.url.isNotEmpty() }?.url,
        contentDescription = stringResource(id = R.string.content_description_category),
        title = category.name
    )
}

@Preview
@Composable
private fun PreviewCategoryItem() {
    CategoryItem(
        category = Category(
            name = "New Releases",
            image = listOf(Image(height = 100, width = 100, url = "https://myimage.png")),
            id = "1234"
        )
    )
}

@Preview
@Composable
private fun PreviewCategoriesContent() {
    CategoriesContent(
        categories = listOf(
            Category(
                name = "New Releases",
                image = listOf(Image(height = 100, width = 100, url = "https://myimage1.png")),
                id = "1234"
            ),
            Category(
                name = "Indie",
                image = listOf(Image(height = 100, width = 100, url = "https://myimage2.png")),
                id = "5678"
            )
        ).toImmutableList()
    )
}