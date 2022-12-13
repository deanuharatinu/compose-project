package com.deanuharatinu.composenote

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.deanuharatinu.composenote.ui.components.ComposeNoteTopBar
import com.deanuharatinu.composenote.ui.navigation.Screen
import com.deanuharatinu.composenote.ui.screen.about.AboutScreen
import com.deanuharatinu.composenote.ui.screen.addnote.AddNoteScreen
import com.deanuharatinu.composenote.ui.screen.detail.NoteDetailScreen
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
            aboutClick = { navController.navigate(Screen.About.route) }
          )
        }
        Screen.About.route -> {
          ComposeNoteTopBar(
            title = stringResource(R.string.about_title_page),
            backClick = { navController.navigateUp() },
          )
        }
        Screen.AddNote.route -> {
          ComposeNoteTopBar(
            title = stringResource(id = R.string.add_note),
            backClick = { navController.navigateUp() }
          )
        }
        else -> {
          ComposeNoteTopBar(
            title = stringResource(R.string.note_detail_title_page),
            backClick = { navController.navigateUp() }
          )
        }
      }
    },
    floatingActionButton = {
      if (currentRoute == Screen.Home.route) {
        FloatingActionButton(onClick = { navController.navigate(Screen.AddNote.route) }) {
          Image(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(R.string.add_note)
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
        HomeScreen(
          navigateToNoteDetail = { noteId ->
            navController.navigate(Screen.NoteDetail.createRoute(noteId))
          }
        )
      }
      composable(Screen.About.route) {
        AboutScreen()
      }
      composable(Screen.AddNote.route) {
        AddNoteScreen(navigateBack = { navController.navigateUp() })
      }
      composable(
        route = Screen.NoteDetail.route,
        arguments = listOf(navArgument("noteId") { type = NavType.StringType }),
      ) {
        val noteId = it.arguments?.getString("noteId") ?: ""
        NoteDetailScreen(
          noteId = noteId,
          navigateBack = {
            navController.navigateUp()
          }
        )
      }
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