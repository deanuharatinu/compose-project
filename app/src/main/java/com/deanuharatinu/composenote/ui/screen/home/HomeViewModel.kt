package com.deanuharatinu.composenote.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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

class HomeViewModel(private val repository: NoteRepository) : ViewModel() {
  private val _uiState: MutableStateFlow<UiState<List<Note>>> = MutableStateFlow(UiState.Loading)
  val uiState: StateFlow<UiState<List<Note>>>
    get() = _uiState

  private val _query = mutableStateOf("")
  val query: State<String> get() = _query

  init {
    getAllNotes()
  }

  private fun getAllNotes() {
    viewModelScope.launch {
      repository.getAllNotes()
        .catch {
          _uiState.value = UiState.Error(it.message.toString())
        }
        .collect { noteList ->
          delay(1000)
          _uiState.value = UiState.Success(noteList)
        }
    }
  }

  fun searchNote(query: String) {
    viewModelScope.launch {
      _uiState.value = UiState.Loading
      _query.value = query
      repository.searchNote(_query.value)
        .catch {
          _uiState.value = UiState.Error(it.message.toString())
        }
        .collect { noteList ->
          if (noteList.isNotEmpty()) {
            _uiState.value = UiState.Success(noteList)
          } else {
            _uiState.value = UiState.Error("No content")
          }
        }
    }
  }
}