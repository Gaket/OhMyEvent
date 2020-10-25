package ru.gaket.ohmyevent.features.events

import ru.gaket.ohmyevent.models.Event

data class EventUiModel(
    val title: String,
    val description: String,
    val tags: List<String>,
    val image: Int?,
    val shortDescription: String?,
    val isLoading: Boolean
) {

    companion object {
        fun from(event: Event): EventUiModel {
            return EventUiModel(
                event.title,
                event.description,
                event.tags,
                event.image,
                event.shortDescription,
                false
            )
        }
    }
}