package ru.gaket.ohmyevent.features.navigation

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.android.parcel.Parcelize

/**
 * Models the screens in the app and any arguments they require.
 */
sealed class Destination : Parcelable {
    @Parcelize
    object EventsList : Destination()

    @Immutable
    @Parcelize
    data class Event(val eventId: Long) : Destination()
}

/**
 * Models the navigation actions in the app.
 */
class Actions(navigator: Navigator<Destination>) {
    val onboardingComplete: () -> Unit = {
        navigator.navigate(Destination.EventsList)
    }
    val selectEvent: (Long) -> Unit = { eventId: Long ->
        navigator.navigate(Destination.Event(eventId))
    }
    val back: () -> Unit = {
        navigator.back()
    }
}
