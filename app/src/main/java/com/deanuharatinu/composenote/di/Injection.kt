package com.deanuharatinu.composenote.di

import com.deanuharatinu.composenote.data.NoteRepository
import com.deanuharatinu.composenote.data.NoteRepositoryImpl

object Injection {
  fun provideRepository(): NoteRepository = NoteRepositoryImpl.getInstance()
}