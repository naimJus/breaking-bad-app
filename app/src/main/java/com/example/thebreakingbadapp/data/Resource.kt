package com.example.thebreakingbadapp.data

sealed class Resource<out T : Any>(
    private val data: T? = null,
    private val message: String? = null,
    private val throwable: Throwable? = null
) {
    data class Success<T : Any>(val data: T) : Resource<T>(data)
    data class Error(val message: String?, val throwable: Throwable? = null) :
        Resource<Nothing>(null, message, throwable)
}