package com.deanuharatinu.composenote.ui.screen.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.deanuharatinu.composenote.R
import com.deanuharatinu.composenote.di.Injection
import com.deanuharatinu.composenote.ui.ViewModelFactory
import com.deanuharatinu.composenote.ui.common.UiState
import com.deanuharatinu.composenote.ui.components.LoadingContent
import com.deanuharatinu.composenote.ui.theme.ComposeNoteTheme

@Composable
fun NoteDetailScreen(
  modifier: Modifier = Modifier,
  noteId: String,
  navigateBack: () -> Unit,
  viewModel: NoteDetailViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
) {
  Column(
    modifier = modifier,
  ) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
      when (uiState) {
        is UiState.Loading -> {
          viewModel.getNoteDetail(noteId)
          Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxSize(),
          ) {
            LoadingContent()
          }
        }
        is UiState.Success -> {
          val note = uiState.data
          NoteDetailContent(
            noteId = note.id,
            photoUrl = note.photoUrl.orEmpty(),
            title = note.title,
            content = note.noteContent,
            author = note.author ?: "No Author",
            onDeleteClick = { noteId ->
              viewModel.deleteNote(noteId)
              navigateBack()
            }
          )
        }
        is UiState.Error -> {
          Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
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
fun NoteDetailScreenPreview() {
  ComposeNoteTheme {
    NoteDetailScreen(
      noteId = "",
      navigateBack = {}
    )
  }
}

@Composable
fun NoteDetailContent(
  modifier: Modifier = Modifier,
  noteId: String,
  title: String,
  author: String,
  photoUrl: String? = null,
  content: String,
  onDeleteClick: (String) -> Unit,
) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(16.dp)
  ) {
    photoUrl?.run {
      AsyncImage(
        model = photoUrl,
        contentDescription = null,
        modifier = Modifier
          .align(Alignment.CenterHorizontally)
          .padding(16.dp)
          .size(160.dp)
          .clip(CircleShape),
        contentScale = ContentScale.Crop
      )
    }
    Text(
      text = title,
      textAlign = TextAlign.Start,
      style = MaterialTheme.typography.h4
    )
    Text(
      text = author,
      textAlign = TextAlign.Start,
      style = MaterialTheme.typography.subtitle2
    )
    Spacer(
      modifier = Modifier
        .padding(vertical = 8.dp)
    )
    Text(
      text = content,
      style = MaterialTheme.typography.caption.copy(
        lineHeight = 18.sp,
        textAlign = TextAlign.Justify
      )
    )
    Spacer(modifier = Modifier.weight(1f))
    Button(
      modifier = Modifier
        .fillMaxWidth(),
      colors = ButtonDefaults.buttonColors(
        backgroundColor = Color.Red,
        contentColor = Color.White
      ),
      onClick = { onDeleteClick(noteId) },
      shape = RoundedCornerShape(16.dp)
    ) {
      Icon(
        imageVector = Icons.Filled.Delete,
        contentDescription = null,
        modifier = Modifier.padding(end = 8.dp)
      )
      Text(
        text = "Delete".uppercase()
      )
    }
  }
}

@Composable
@Preview(showBackground = true)
fun NoteDetailContentPreview() {
  ComposeNoteTheme {
    NoteDetailContent(
      noteId = "",
      photoUrl = null,
      title = "This is a tile",
      content = "Lorem ipsum dolor sit amet".repeat(10),
      author = "This is an Author",
      onDeleteClick = {}
    )
  }
}