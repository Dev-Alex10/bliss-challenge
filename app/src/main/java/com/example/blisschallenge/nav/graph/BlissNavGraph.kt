package com.example.blisschallenge.nav.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.blisschallenge.nav.destinations.Avatar
import com.example.blisschallenge.nav.destinations.EmojiList
import com.example.blisschallenge.nav.destinations.Home
import com.example.blisschallenge.nav.destinations.RepoList
import com.example.blisschallenge.ui.MainView
import com.example.blisschallenge.ui.emoji.EmojiView

@Composable
fun BlissNavHost(navHostController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navHostController,
        startDestination = Home.route,
        modifier = modifier
    ) {
        composable(Home.route) {
            MainView(
                onClickEmojiList = { navHostController.navigate(EmojiList.route) },
                modifier = modifier
            )
        }
        composable(EmojiList.route) {
            EmojiView(modifier = modifier)
        }
        composable(Avatar.route) {

        }
        composable(RepoList.route) {

        }
    }
}