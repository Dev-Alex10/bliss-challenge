package com.example.blisschallenge.nav.destinations

interface BlissAppDestination {
    val route: String
}
object Home: BlissAppDestination {
    override val route = "Home"
}
object EmojiList: BlissAppDestination {
    override val route = "EmojiList"
}
object Avatar: BlissAppDestination {
    override val route = "Avatar"
}
object RepoList: BlissAppDestination {
    override val route = "RepoList"
}
