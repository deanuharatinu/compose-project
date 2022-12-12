package com.deanuharatinu.composenote.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deanuharatinu.composenote.ui.theme.ComposeNoteTheme

@Composable
fun AddNoteTextField(
  modifier: Modifier = Modifier,
  title: String,
  placeholder: String,
  onValueChanged: (String) -> Unit,
) {
  var value by remember { mutableStateOf("") }

  Column(modifier = modifier) {
    Text(
      text = title,
      modifier = Modifier.fillMaxWidth(),
      style = MaterialTheme.typography.h6
    )
    TextField(
      value = value,
      onValueChange = {
        onValueChanged(it)
        value = it
      },
      placeholder = {
        Text(text = placeholder)
      },
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 4.dp)
        .clip(RoundedCornerShape(16.dp)),
      colors = TextFieldDefaults.textFieldColors(
        backgroundColor = MaterialTheme.colors.surface,
        disabledIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent
      )
    )
  }
}

@Composable
@Preview(showBackground = true)
fun AddNoteTextFieldPreview() {
  ComposeNoteTheme {
    AddNoteTextField(title = "Title", placeholder = "Add Title", onValueChanged = {})
  }
}