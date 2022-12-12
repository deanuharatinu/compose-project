package com.deanuharatinu.composenote

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.deanuharatinu.composenote.ui.components.ComposeNoteTopBar
import com.deanuharatinu.composenote.ui.navigation.Screen
import com.deanuharatinu.composenote.ui.screen.home.HomeScreen
import com.deanuharatinu.composenote.ui.theme.ComposeNoteTheme

@Composable
fun ComposeNoteApp(
  modifier: Modifier = Modifier,
  navController: NavHostController = rememberNavController()
) {
  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentRoute = navBackStackEntry?.destination?.route

  Scaffold(
    modifier = modifier,
    topBar = {
      when (currentRoute) {
        Screen.Home.route -> {
          ComposeNoteTopBar(
            title = stringResource(R.string.home_page_title),
            aboutClick = {}
          )
        }
        Screen.About.route -> {
          ComposeNoteTopBar(
            title = stringResource(R.string.about_title_page),
            backClick = {}
          )
        }
        else -> {
          ComposeNoteTopBar(
            backClick = {}
          )
        }
      }
    }
  ) { innerPadding ->
    NavHost(
      navController = navController,
      startDestination = Screen.Home.route,
      modifier = Modifier.padding(innerPadding)
    ) {
      composable(Screen.Home.route) {
        HomeScreen()
      }
      composable(Screen.About.route) { }
      composable(
        route = Screen.NoteDetail.route,
      ) { }
    }
  }
}

@Composable
@Preview(showBackground = true)
fun ComposeNoteAppPreview() {
  ComposeNoteTheme {
    ComposeNoteApp()
  }
}