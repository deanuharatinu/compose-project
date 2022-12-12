package com.deanuharatinu.composenote.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deanuharatinu.composenote.data.NoteRepository
import com.deanuharatinu.composenote.model.Note
import com.deanuharatinu.composenote.ui.common.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class NoteDetailViewModel(private val repository: NoteRepository) : ViewModel() {
  private val _uiState: MutableStateFlow<UiState<Note>> = MutableStateFlow(UiState.Loading)
  val uiState: StateFlow<UiState<Note>>
    get() = _uiState

  fun getNoteDetail(noteId: String) {
    viewModelScope.launch {
      repository.getNote(noteId)
        .catch {
          _uiState.value = UiState.Error(it.message.toString())
        }
        .collect { note ->
          delay(1000)
          _uiState.value = UiState.Success(note)
        }
    }
  }

  fun deleteNote(noteId: String) {
    viewModelScope.launch {
      repository.deleteNote(noteId = noteId)
    }
  }
}