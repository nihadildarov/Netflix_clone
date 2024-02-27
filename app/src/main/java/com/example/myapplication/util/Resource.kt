package com.example.myapplication.util

sealed class Resource<out T: Any> {
    data class Success<out T: Any>(val data:T):Resource<T>()
    data class Error(val exception: Throwable):Resource<Nothing>()
    data object Loading: Resource<Nothing>()
}