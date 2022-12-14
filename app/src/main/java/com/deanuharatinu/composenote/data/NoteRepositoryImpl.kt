package com.deanuharatinu.composenote.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.deanuharatinu.composenote.model.About
import com.deanuharatinu.composenote.model.FakeNoteDataSource
import com.deanuharatinu.composenote.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class NoteRepositoryImpl : NoteRepository {
  private val noteList = mutableListOf<Note>()
  private val about = About(
    name = "deanuharatinu",
    email = "deanu.alt@gmail.com",
    photoUrl = null
  )

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

  override fun getNote(noteId: String): Flow<Note> {
    val noteList = noteList.filter { it.id == noteId }
    return flowOf(noteList[0])
  }

  @RequiresApi(Build.VERSION_CODES.N)
  override fun deleteNote(noteId: String) {
    noteList.removeIf { it.id == noteId }
  }

  override fun addNote(note: Note) {
    noteList.add(note)
  }

  override fun getAbout(): Flow<About> = flowOf(about)

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