package com.example.thebreakingbadapp.data

import androidx.annotation.StringRes

sealed class Message(private val resource: Int? = null, private val text: String? = null) {
    data class Resource(@StringRes val messageRes: Int) : Message(resource = messageRes)
    data class Text(val message: String?) : Message(text = message)
}