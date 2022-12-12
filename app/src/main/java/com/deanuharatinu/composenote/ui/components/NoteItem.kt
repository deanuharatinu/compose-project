package com.deanuharatinu.composenote.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deanuharatinu.composenote.ui.theme.ComposeNoteTheme

@Composable
fun NoteItem(
  noteId: String,
  title: String,
  content: String,
  modifier: Modifier = Modifier,
  onCardClick: (noteId: String) -> Unit,
) {
  Card(
    modifier = modifier
      .fillMaxWidth()
      .clickable { onCardClick(noteId) },
    shape = RoundedCornerShape(16.dp),
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      Text(
        text = title,
        textAlign = TextAlign.Start,
        style = MaterialTheme.typography.h5
      )
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = content.repeat(5),
        maxLines = 5,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.caption.copy(
          lineHeight = 18.sp
        )
      )
    }
  }
}

@Composable
@Preview(showBackground = true)
fun NoteItemPreview() {
  ComposeNoteTheme {
    NoteItem(
      noteId = "123",
      title = "How to get a Book",
      content = "Lorem ipsum dolor sit amet, lorem ipsum dolor sit amet",
      onCardClick = {}
    )
  }
}
