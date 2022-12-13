package com.deanuharatinu.composenote.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.deanuharatinu.composenote.ui.theme.ComposeNoteTheme

@Composable
fun ComposeNoteTopBar(
  modifier: Modifier = Modifier,
  title: String = "",
  backClick: (() -> Unit)? = null,
  aboutClick: (() -> Unit)? = null
) {
  TopAppBar(
    modifier = modifier,
    title = { Text(text = title, fontWeight = FontWeight.Bold) },
    navigationIcon = if (backClick != null) {
      {
        IconButton(onClick = backClick) {
          Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }
      }
    } else {
      null
    },
    actions = {
      aboutClick?.run {
        IconButton(onClick = aboutClick) {
          Icon(imageVector = Icons.Filled.Person, contentDescription = "about_page")
        }
      }
    }
  )
}

@Composable
@Preview(showBackground = true)
fun ComposeNoteTopBarPreview() {
  ComposeNoteTheme {
    ComposeNoteTopBar(title = "Compose Note App")
  }
}