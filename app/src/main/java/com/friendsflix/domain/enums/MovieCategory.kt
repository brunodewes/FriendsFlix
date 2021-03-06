package com.friendsflix.domain.enums

enum class MovieCategory(
    val title: String
) {
    TOP_RATED("Top Rated"),
    POPULAR("Popular"),
    NEW("New"),
    ACTION("Action"),
    UPCOMING("Upcoming"),
    FAVORITE("Favorites")
}