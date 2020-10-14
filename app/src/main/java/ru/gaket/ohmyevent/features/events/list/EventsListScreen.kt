package ru.gaket.ohmyevent.features.events.list

import android.R.color
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.state
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.androidacademy.sections.list.ScreenState
import com.example.androidacademy.ui.typography
import ru.gaket.ohmyevent.features.events.EventUiModel


@Composable
fun EventsListScreen(state: ScreenState<List<EventUiModel>>) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Events")
                },
                actions = {
                    if (state is ScreenState.Loading)
                        CircularProgressIndicator(
                            color = MaterialTheme.colors.secondary,
                            modifier = Modifier
                                .padding(8.dp)
                        )
                }
            )
        },
        bodyContent = {
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
    )
}

@Composable
fun PhoneTextField() {
    TextField(
        value = "Always hello",
        onValueChange = {
            // do nothing
        },
        label = { Text("Label") }
    )
}

@Composable
fun ErrorScreen(errorMsg: String) {
    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
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
