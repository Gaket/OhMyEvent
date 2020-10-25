package ru.gaket.ohmyevent

import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.badoo.ribs.android.RibActivity
import com.badoo.ribs.core.Rib
import com.example.androidacademy.R
import com.example.androidacademy.sections.list.ScreenState
import com.example.androidacademy.ui.EventTheme
import ru.gaket.ohmyevent.data.EventsProvider
import ru.gaket.ohmyevent.data.EventsRepository
import ru.gaket.ohmyevent.features.events.EventUiModel
import ru.gaket.ohmyevent.features.events.details.EventDetailsScreen
import ru.gaket.ohmyevent.features.events.list.EventListViewModel
import ru.gaket.ohmyevent.features.events.list.EventsListScreen

class MainActivity : RibActivity() {

    private val locationsViewModel: EventListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return EventListViewModel(EventsRepository(EventsProvider(this@MainActivity))) as T
            }
        }
    }
    override val rootViewGroup: ViewGroup
        get() = TODO("Not yet implemented")

    override fun createRib(savedInstanceState: Bundle?): Rib {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        locationsViewModel.eventsListState.observe(this) {
            setContent(it)
        }

    }

    fun setContent(state: ScreenState<List<EventUiModel>>) {
        val showDetails = false
        setContent {
            EventTheme {
                if (showDetails) {
                    EventDetailsScreen(event = EventUiModel.from(EventsProvider(this).events.first()))
                } else {
                    EventsListScreen(state)
                }
            }
        }
    }
}
