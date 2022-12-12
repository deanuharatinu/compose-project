package com.deanuharatinu.composenote.ui.screen.addnote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deanuharatinu.composenote.data.NoteRepository
import com.deanuharatinu.composenote.model.Note
import kotlinx.coroutines.launch

class AddNoteViewModel(private val repository: NoteRepository) : ViewModel() {
  fun addNote(note: Note) {
    viewModelScope.launch {
      repository.addNote(note)
    }
  }
}