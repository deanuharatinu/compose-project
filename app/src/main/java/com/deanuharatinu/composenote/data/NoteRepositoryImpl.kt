package com.deanuharatinu.composenote.data

import com.deanuharatinu.composenote.model.FakeNoteDataSource
import com.deanuharatinu.composenote.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class NoteRepositoryImpl : NoteRepository {
  private val noteList = mutableListOf<Note>()

  init {
    if (noteList.isEmpty()) {
      FakeNoteDataSource.dummyNote.forEach {
        noteList.add(it)
      }
    }
  }

  override fun getAllNotes(): Flow<List<Note>> = flowOf(noteList)

  override fun searchNote(query: String): Flow<List<Note>> {
    return flowOf(
      noteList.filter {
        it.title.contains(query, ignoreCase = true)
      }
    )
  }

  companion object {
    @Volatile
    private var instance: NoteRepository? = null

    fun getInstance(): NoteRepository =
      instance ?: synchronized(this) {
        NoteRepositoryImpl().apply {
          instance = this
        }
      }
  }
}