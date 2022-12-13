package com.deanuharatinu.composenote.ui.screen.about

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.deanuharatinu.composenote.R
import com.deanuharatinu.composenote.di.Injection
import com.deanuharatinu.composenote.ui.ViewModelFactory
import com.deanuharatinu.composenote.ui.common.UiState
import com.deanuharatinu.composenote.ui.components.LoadingContent
import com.deanuharatinu.composenote.ui.theme.ComposeNoteTheme

@Composable
fun AboutScreen(
  modifier: Modifier = Modifier,
  viewModel: AboutViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
) {
  viewModel.uiState.collectAsState().value.let { uiState ->
    when (uiState) {
      is UiState.Loading -> {
        Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center,
          modifier = modifier.fillMaxSize(),
        ) {
          LoadingContent()
        }
        viewModel.getAbout()
      }
      is UiState.Success -> {
        val about = uiState.data
        AboutScreenContent(
          modifier = modifier
            .fillMaxSize()
            .padding(top = 100.dp),
          username = about.name,
          email = about.email,
          photo = R.drawable.avatar,
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

@Composable
fun AboutScreenContent(
  modifier: Modifier = Modifier,
  username: String,
  email: String,
  @DrawableRes photo: Int,
) {
  Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Image(
      painter = painterResource(id = photo),
      contentDescription = "Avatar",
      modifier = Modifier
        .size(200.dp)
        .clip(CircleShape)
    )
    Spacer(modifier = Modifier.padding(top = 16.dp))
    Text(
      text = username,
      style = MaterialTheme.typography.h5
    )
    Text(
      text = email,
      style = MaterialTheme.typography.h6
    )
  }
}

@Composable
@Preview(showBackground = true)
fun AboutScreenPreview() {
  ComposeNoteTheme {
    AboutScreen()
  }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun AboutScreenContentPreview() {
  ComposeNoteTheme {
    AboutScreenContent(
      username = "deanuharatinu",
      email = "deanu.alt@gmail.com",
      photo = R.drawable.avatar,
      modifier = Modifier
        .fillMaxSize()
        .padding(top = 100.dp),
    )
  }
}