package com.deanuharatinu.composenote.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.deanuharatinu.composenote.data.NoteRepository
import com.deanuharatinu.composenote.ui.screen.detail.NoteDetailViewModel
import com.deanuharatinu.composenote.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: NoteRepository) :
  ViewModelProvider.NewInstanceFactory() {
  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
      return HomeViewModel(repository) as T
    } else if (modelClass.isAssignableFrom(NoteDetailViewModel::class.java)) {
      return NoteDetailViewModel(repository) as T
    }

    throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
  }
}