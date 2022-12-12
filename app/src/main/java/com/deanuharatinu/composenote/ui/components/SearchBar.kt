package com.deanuharatinu.composenote.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deanuharatinu.composenote.R
import com.deanuharatinu.composenote.ui.theme.ComposeNoteTheme

@Composable
fun SearchBar(
  query: String,
  modifier: Modifier = Modifier,
  onQueryChange: (String) -> Unit,
) {
  TextField(
    value = query,
    onValueChange = onQueryChange,
    modifier = modifier
      .fillMaxWidth()
      .heightIn(48.dp)
      .clip(RoundedCornerShape(16.dp)),
    maxLines = 1,
    leadingIcon = {
      Icon(
        imageVector = Icons.Default.Search,
        contentDescription = null,
      )
    },
    placeholder = {
      Text(
        text = stringResource(R.string.placeholder_search)
      )
    },
    colors = TextFieldDefaults.textFieldColors(
      backgroundColor = MaterialTheme.colors.surface,
      disabledIndicatorColor = Color.Transparent,
      focusedIndicatorColor = Color.Transparent,
      unfocusedIndicatorColor = Color.Transparent
    )
  )
}

@Composable
@Preview(showBackground = true)
fun SearchBarPreview() {
  ComposeNoteTheme {
    SearchBar(query = "", onQueryChange = {})
  }
}