package com.deanuharatinu.composenote.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.deanuharatinu.composenote.di.Injection
import com.deanuharatinu.composenote.model.FakeNoteDataSource
import com.deanuharatinu.composenote.model.Note
import com.deanuharatinu.composenote.ui.ViewModelFactory
import com.deanuharatinu.composenote.ui.common.UiState
import com.deanuharatinu.composenote.ui.components.LoadingContent
import com.deanuharatinu.composenote.ui.components.NoteItem
import com.deanuharatinu.composenote.ui.components.SearchBar
import com.deanuharatinu.composenote.ui.theme.ComposeNoteTheme

@Composable
fun HomeScreen(
  modifier: Modifier = Modifier,
  viewModel: HomeViewModel = viewModel(
    factory = ViewModelFactory(Injection.provideRepository())
  )
) {
  val query by viewModel.query

  Column(modifier = modifier) {
    // search bar

    // screen content
  }

  viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
    when (uiState) {
      is UiState.Loading -> {
        viewModel.getAllNotes()
        Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center,
          modifier = modifier.fillMaxSize(),
        ) {
          LoadingContent()
        }
      }
      is UiState.Success -> {
        HomeContent(
          noteList = uiState.data,
          modifier = modifier,
          navigateToNoteDetail = { noteId ->
          },
          onSearch = { searchQuery ->
            viewModel.searchNote(searchQuery)
          },
          query = query,
        )
      }
      is UiState.Error -> {}
    }
  }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
fun HomeScreenPreview() {
  ComposeNoteTheme {
    HomeScreen()
  }
}

@Composable
fun HomeContent(
  modifier: Modifier = Modifier,
  noteList: List<Note>,
  navigateToNoteDetail: (noteId: String) -> Unit,
  onSearch: (String) -> Unit,
  query: String,
) {

  Column(modifier = modifier) {
    SearchBar(
      query = query,
      onQueryChange = onSearch,
      modifier = Modifier
        .padding(vertical = 16.dp, horizontal = 8.dp)
    )

    LazyColumn(contentPadding = PaddingValues(top = 16.dp, bottom = 50.dp)) {
      items(noteList) { note ->
        NoteItem(
          noteId = note.id,
          title = note.title,
          content = note.noteContent,
          modifier = Modifier.padding(8.dp),
          onCardClick = { noteId ->
            navigateToNoteDetail(noteId)
          }
        )
      }
    }
  }
}

@Composable
@Preview(showBackground = true)
fun HomeContentPreview() {
  ComposeNoteTheme {
    HomeContent(
      noteList = FakeNoteDataSource.dummyNote,
      navigateToNoteDetail = {},
      onSearch = {},
      query = ""
    )
  }
}