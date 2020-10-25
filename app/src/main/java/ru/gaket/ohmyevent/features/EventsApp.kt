package ru.gaket.ohmyevent.features

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.savedinstancestate.rememberSavedInstanceState
import com.example.androidacademy.ui.EventTheme
import ru.gaket.ohmyevent.features.navigation.ProvideDisplayInsets
import ru.gaket.ohmyevent.features.events.list.EventsList
import ru.gaket.ohmyevent.features.navigation.Actions
import ru.gaket.ohmyevent.features.navigation.Destination
import ru.gaket.ohmyevent.features.navigation.Navigator


@Composable
fun EventsApp(backDispatcher: OnBackPressedDispatcher) {
    val navigator: Navigator<Destination> = rememberSavedInstanceState(
        saver = Navigator.saver<Destination>(backDispatcher)
    ) {
        Navigator(Destination.EventsList, backDispatcher)
    }
    val actions = remember(navigator) { Actions(navigator) }
    ProvideDisplayInsets {
        EventTheme() {
            Crossfade(navigator.current) { destination ->
                when (destination) {
                    Destination.EventsList -> EventsList(emptyList(), {})
                }
            }
        }
    }
}
