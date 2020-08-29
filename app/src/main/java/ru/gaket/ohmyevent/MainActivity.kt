package ru.gaket.ohmyevent

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidacademy.R
import com.example.androidacademy.sections.list.ScreenState
import com.example.androidacademy.ui.EventTheme
import ru.gaket.ohmyevent.data.EventsProvider
import ru.gaket.ohmyevent.data.EventsRepository
import ru.gaket.ohmyevent.features.events.EventUiModel
import ru.gaket.ohmyevent.features.events.list.EventListViewModel
import ru.gaket.ohmyevent.features.events.list.EventsListScreen

class MainActivity : AppCompatActivity() {

    private val locationsViewModel: EventListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return EventListViewModel(EventsRepository(EventsProvider(this@MainActivity))) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        locationsViewModel.eventsListState.observe(this) {
            setContent(it)
        }
    }

    fun setContent(state: ScreenState<List<EventUiModel>>) {
        setContent {
            EventTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(title = {
                            Text(getString(R.string.title_events_list))
                        })
                    },
                    bodyContent = {
                        EventsListScreen(state)
                    }
                )
            }
        }
    }
}
