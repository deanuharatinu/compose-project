package com.deanuharatinu.composenote.ui.screen.addnote

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.deanuharatinu.composenote.R
import com.deanuharatinu.composenote.di.Injection
import com.deanuharatinu.composenote.model.Note
import com.deanuharatinu.composenote.ui.ViewModelFactory
import com.deanuharatinu.composenote.ui.common.UiState
import com.deanuharatinu.composenote.ui.components.AddNoteTextField
import com.deanuharatinu.composenote.ui.theme.ComposeNoteTheme

@Composable
fun AddNoteScreen(
  modifier: Modifier = Modifier,
  navigateBack: () -> Unit,
  viewModel: AddNoteViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
) {
  val context = LocalContext.current

  AddNoteContent(
    modifier = modifier,
    onAddNoteClick = viewModel::addNote
  )

  viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
    when (uiState) {
      is UiState.Loading -> {
        // Empty
      }
      is UiState.Error -> {
        Toast.makeText(context, stringResource(id = R.string.warning_add_note), Toast.LENGTH_SHORT)
          .show()
        viewModel.resetUiState()
      }
      is UiState.Success -> navigateBack()
    }
  }
}

@Composable
fun AddNoteContent(
  modifier: Modifier = Modifier,
  onAddNoteClick: (Note) -> Unit
) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(16.dp)
  ) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }

    // Title
    AddNoteTextField(
      title = "Title",
      onValueChanged = { title = it },
      placeholder = stringResource(R.string.add_note_title_placeholder)
    )

    // Note Content
    AddNoteTextField(
      modifier = Modifier.padding(top = 16.dp),
      title = "Note Content",
      onValueChanged = { content = it },
      placeholder = stringResource(R.string.add_note_content_placeholder)
    )

    // Author
    AddNoteTextField(
      modifier = Modifier.padding(top = 16.dp),
      title = "Author",
      onValueChanged = { author = it },
      placeholder = stringResource(R.string.add_author_placeholder)
    )

    Spacer(modifier = Modifier.weight(1f))

    Button(
      modifier = Modifier
        .fillMaxWidth(),
      colors = ButtonDefaults.buttonColors(
        backgroundColor = Color.Green,
        contentColor = Color.Black
      ),
      onClick = {
        val note = Note(
          title = title,
          photoUrl = null,
          noteContent = content,
          author = author,
          dateCreated = System.currentTimeMillis().toString()
        )
        onAddNoteClick(note)
      },
      shape = RoundedCornerShape(16.dp)
    ) {
      Icon(
        imageVector = Icons.Default.AddCircle,
        contentDescription = null,
        modifier = Modifier.padding(end = 8.dp)
      )
      Text(
        text = "Add note".uppercase()
      )
    }
  }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
fun AddNoteScreenPreview() {
  ComposeNoteTheme {
    AddNoteScreen(navigateBack = {})
  }
}