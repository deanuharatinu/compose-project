package com.deanuharatinu.composenote.ui.components

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.deanuharatinu.composenote.ui.theme.ComposeNoteTheme

@Composable
fun LoadingContent(modifier: Modifier = Modifier) {
  CircularProgressIndicator(modifier = modifier)
}

@Composable
@Preview(showBackground = true)
private fun LoadingContentPreview() {
  ComposeNoteTheme {
    LoadingContent()
  }
}