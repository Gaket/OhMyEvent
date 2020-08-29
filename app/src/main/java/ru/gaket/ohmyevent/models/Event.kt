package ru.gaket.ohmyevent.models

data class Event(
    val id: String,
    val title: String,
    val description: String,
    val shortDescription: String?,
    val url: String,
    val tags: List<String>,
    val image: Int?
)
