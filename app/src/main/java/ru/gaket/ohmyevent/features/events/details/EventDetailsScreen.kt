package ru.gaket.ohmyevent.features.events.details

import androidx.annotation.DrawableRes
import androidx.compose.animation.animate
import androidx.compose.foundation.*
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import androidx.ui.tooling.preview.PreviewParameter
import androidx.ui.tooling.preview.PreviewParameterProvider
import com.example.androidacademy.R
import com.example.androidacademy.ui.purple200
import com.example.androidacademy.ui.typography
import ru.gaket.ohmyevent.features.events.EventUiModel

@Preview
@Composable
fun EventDetailsScreen(
    @PreviewParameter(FakeEventProvider::class) event: EventUiModel
) {
    Scaffold(
        topBar = {
            DetailsTopAppBar(event)
        },
        bodyContent = {
            EventDetail(event)
        }
    )
}

@Composable
private fun DetailsTopAppBar(
    event: EventUiModel
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(Icons.Default.ArrowBack)
            }
        },
        title = { Text(event.title) },
        actions = {
            TextButton(
                enabled = true,
                contentColor = contentColor(),
                disabledContentColor = EmphasisAmbient.current.disabled.applyEmphasis(contentColor()),
                onClick = { }
            ) {
                Text(stringResource(R.string.save))
            }
        }
    )
}

@Composable
private fun EventDetail(event: EventUiModel) {
    ScrollableColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalGravity = Alignment.CenterHorizontally
    ) {
        EventAvatar(event.image)
        Text(
            text = "${event.shortDescription}",
            style = typography.h6,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )
        Text(
            text = "${event.description}",
            style = typography.body1,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )
        LikedIndicator()
    }
}

@Composable
fun EventAvatar(@DrawableRes image: Int?) {
    if (image == null) return
    val resource = imageResource(id = image)
    Image(
        resource,
        modifier = Modifier.size(240.dp)
            .padding(16.dp)
            .drawShadow(8.dp, CircleShape),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun LikedIndicator() {
    val selected = mutableStateOf(false)
    IconButton(
        onClick = { selected.value = !selected.value },
        Modifier.size(72.dp)
    ) {
        val icon = if (selected.value) Icons.Default.Star else Icons.Default.StarBorder
        val color = animate(if (selected.value) purple200 else Color.Black)
        Icon(icon, tint = color)
    }
}

class FakeEventProvider : PreviewParameterProvider<EventUiModel> {
    override val values = sequenceOf(
        EventUiModel(
            "Android Academy",
            "Best event in the world",
            listOf("android", "ios"),
            R.drawable.ic_launcher_foreground,
            "Short event",
            false
        )
    )
    override val count: Int = values.count()
}