package com.deanuharatinu.composenote.ui.screen.about

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deanuharatinu.composenote.data.NoteRepository
import com.deanuharatinu.composenote.model.About
import com.deanuharatinu.composenote.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class AboutViewModel(private val repository: NoteRepository) : ViewModel() {
  private val _uiState: MutableStateFlow<UiState<About>> = MutableStateFlow(UiState.Loading)
  val uiState: StateFlow<UiState<About>>
    get() = _uiState

  fun getAbout() {
    viewModelScope.launch {
      repository.getAbout()
        .catch {
          _uiState.value = UiState.Error(it.message.toString())
        }
        .collect { about ->
          _uiState.value = UiState.Success(about)
        }
    }
  }
}