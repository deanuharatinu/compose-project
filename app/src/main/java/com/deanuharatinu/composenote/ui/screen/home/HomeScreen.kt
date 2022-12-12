package com.deanuharatinu.composenote.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.deanuharatinu.composenote.R
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
  ),
  navigateToNoteDetail: (noteId: String) -> Unit,
) {
  val query by viewModel.query

  if (query.isNotEmpty()) {
    viewModel.searchNote(query)
  }

  Column(modifier = modifier) {
    // Search bar
    SearchBar(
      query = query,
      onQueryChange = viewModel::searchNote,
      modifier = Modifier
        .padding(vertical = 16.dp, horizontal = 8.dp)
    )

    // Screen content
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
      when (uiState) {
        is UiState.Loading -> {
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
            navigateToNoteDetail = navigateToNoteDetail,
          )
        }
        is UiState.Error -> {
          Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxSize(),
          ) {
            Text(text = stringResource(R.string.no_content))
          }
        }
      }
    }
  }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
fun HomeScreenPreview() {
  ComposeNoteTheme {
    HomeScreen(navigateToNoteDetail = {})
  }
}

@Composable
fun HomeContent(
  modifier: Modifier = Modifier,
  noteList: List<Note>,
  navigateToNoteDetail: (noteId: String) -> Unit,
) {
  LazyColumn(
    contentPadding = PaddingValues(top = 16.dp, bottom = 50.dp),
    modifier = modifier,
  ) {
    items(noteList, key = { it.id }) { note ->
      NoteItem(
        modifier = Modifier.padding(8.dp),
        noteId = note.id,
        title = note.title,
        content = note.noteContent,
        photoUrl = note.photoUrl
      ) { noteId ->
        navigateToNoteDetail(noteId)
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
    )
  }
}