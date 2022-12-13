package com.deanuharatinu.composenote.model

import androidx.annotation.DrawableRes

data class About(
  val name: String,
  val email: String,
  @DrawableRes val photoUrl: Int? = null,
)