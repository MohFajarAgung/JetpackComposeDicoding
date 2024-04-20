package com.dicoding.listmusik.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object About : Screen("about")
    object Favorite : Screen("favorite")
    object DetailMusik : Screen("home/{musikId}") {
        fun createRoute(musikId: Long) = "home/$musikId"
    }
}