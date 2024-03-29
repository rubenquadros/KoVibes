package io.github.rubenquadros.kovibes.api.response

data class Categories(
    val items: List<Category>,
    val isNext: Boolean
)

data class Category(
    val name: String,
    val image: List<Image>,
    val id: String
)
