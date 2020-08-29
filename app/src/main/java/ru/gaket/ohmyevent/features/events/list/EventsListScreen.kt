package ru.gaket.ohmyevent.features.events.list

import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androidacademy.sections.list.ScreenState
import com.example.androidacademy.ui.typography
import ru.gaket.ohmyevent.features.events.EventUiModel


@Composable
fun EventsListScreen(state: ScreenState<List<EventUiModel>>) {
    when (state) {
        is ScreenState.Loading<*> -> LoadingScreen()
        is ScreenState.Success<List<EventUiModel>> -> {
            EventsList(
                plants = state.data,
                onEventClicked = {
                    // TODO: navigate
                })
        }
        is ScreenState.Error -> ErrorScreen(state.error)
    }
}

@Composable
fun ErrorScreen(errorMsg: String) {
    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalGravity = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$errorMsg :("
        )
    }
}

@Composable
fun LoadingScreen() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        CircularProgressIndicator(
            Modifier
                .wrapContentHeight()
                .wrapContentWidth()
        )
    }
}

@Composable
fun EventsList(plants: List<EventUiModel>, onEventClicked: (EventUiModel) -> Unit) {
    LazyColumnFor(plants) { plant ->
        EventCard(plant, onEventClicked)
    }
}

@Composable
fun EventCard(event: EventUiModel, callback: (EventUiModel) -> Unit) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable(onClick = {
                callback(event)
            })
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "${event.title}",
                style = typography.h6
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${event.shortDescription}",
                style = typography.body2
            )
        }
    }
}
