package com.deanuharatinu.composenote.ui.navigation

sealed class Screen(val route: String) {
  object Home : Screen("home")
  object About : Screen("about")
  object AddNote: Screen("add_note")
  object NoteDetail : Screen("home/{noteId}") {
    fun createRoute(noteId: String) = "home/$noteId"
  }
}