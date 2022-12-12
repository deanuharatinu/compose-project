package com.deanuharatinu.composenote.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.deanuharatinu.composenote.ui.theme.ComposeNoteTheme

@Composable
fun NoteItem(
  modifier: Modifier = Modifier,
  noteId: String,
  title: String,
  content: String,
  photoUrl: String? = null,
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
      Row {
        photoUrl?.run {
          AsyncImage(
            model = photoUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
              .padding(end = 12.dp)
              .size(40.dp)
              .clip(CircleShape)
          )
        }
        Text(
          text = content,
          maxLines = 5,
          overflow = TextOverflow.Ellipsis,
          style = MaterialTheme.typography.caption.copy(
            lineHeight = 18.sp,
            textAlign = TextAlign.Justify
          ),
        )
      }
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
      content = "Lorem ipsum dolor sit amet, lorem ipsum dolor sit amet"
    ) {}
  }
}