package com.deanuharatinu.composenote.ui.screen.addnote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deanuharatinu.composenote.data.NoteRepository
import com.deanuharatinu.composenote.model.Note
import com.deanuharatinu.composenote.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddNoteViewModel(private val repository: NoteRepository) : ViewModel() {
  private val _uiState: MutableStateFlow<UiState<Boolean>> = MutableStateFlow(UiState.Loading)
  val uiState: StateFlow<UiState<Boolean>>
    get() = _uiState

  fun addNote(note: Note) {
    viewModelScope.launch {
      if (note.title.isNotEmpty() &&
        note.noteContent.isNotEmpty() &&
        note.author?.isNotEmpty() == true
      ) {
        repository.addNote(note)
        _uiState.value = UiState.Success(true)
      } else {
        _uiState.value = UiState.Error("Error")
      }
    }
  }

  fun resetUiState() {
    _uiState.value = UiState.Loading
  }
}