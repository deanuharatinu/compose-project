package com.deanuharatinu.composenote.data

import com.deanuharatinu.composenote.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
  fun getAllNotes(): Flow<List<Note>>

  fun searchNote(query: String): Flow<List<Note>>

  fun getNote(noteId: String): Flow<Note>

  fun deleteNote(noteId: String)
}