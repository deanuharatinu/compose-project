package com.deanuharatinu.composenote.model

import java.util.*

data class Note(
  val id: String = UUID.randomUUID().toString(),
  val title: String,
  val noteContent: String,
  val author: String? = null,
  val dateCreated: String? = null,
  val dateUpdated: String? = null,
  val photoUrl: String? = null,
)