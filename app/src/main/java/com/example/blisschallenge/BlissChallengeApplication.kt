package com.example.blisschallenge

import android.app.Application
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.blisschallenge.ui.theme.BlissChallengeTheme
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class BlissChallengeApplication @Inject constructor() : Application()

@Composable
fun BlissChallengeApp() {
    BlissChallengeTheme {
        Greeting("Android")
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
