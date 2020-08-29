package com.example.androidacademy.sections.list

sealed class ScreenState<out T> {
    class Loading<out T> : ScreenState<T>()
    data class Error(val error: String) : ScreenState<Nothing>()
    class Success<out T>(val data: T) : ScreenState<T>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$error]"
            is Loading -> "Loading"
        }
    }
}
