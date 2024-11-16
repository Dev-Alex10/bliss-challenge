package com.example.blisschallenge

import android.app.Application
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.blisschallenge.nav.graph.BlissNavHost
import com.example.blisschallenge.ui.theme.BlissChallengeTheme
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class BlissChallengeApplication @Inject constructor() : Application()

@Composable
fun BlissChallengeApp() {
    BlissChallengeTheme {
        val navController = rememberNavController()
        Scaffold()
        {
            BlissNavHost(
                navHostController = navController, modifier = Modifier.padding(it)
            )
        }
    }
}
