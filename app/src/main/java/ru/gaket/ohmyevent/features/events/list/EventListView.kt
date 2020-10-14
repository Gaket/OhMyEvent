package ru.gaket.ohmyevent.features.events.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.LayoutRes
import androidx.compose.foundation.Text
import androidx.compose.ui.platform.ComposeView
import com.badoo.ribs.core.view.RibView
import com.badoo.ribs.core.view.ViewFactory
import com.example.androidacademy.R
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.ObservableSource
import io.reactivex.functions.Consumer


interface EventListView : RibView,
    ObservableSource<EventListView.Event>,
    Consumer<EventListView.ViewModel> {

    sealed class Event {
        object ButtonClicked : Event()
    }

    data class ViewModel(
        val i: Int = 0
    )

    interface Factory : ViewFactory<Nothing?, EventListView>
}

class HelloWorldViewImpl private constructor(
    override val androidView: ViewGroup,
    private val events: PublishRelay<EventListView.Event> = PublishRelay.create()
) : RibView,
    EventListView,
    ObservableSource<EventListView.Event> by events,
    Consumer<EventListView.ViewModel> {

    class Factory(
        @LayoutRes private val layoutRes: Int = R.layout.compose_layout
    ) : EventListView.Factory {
        override fun invoke(deps: Nothing?): (ViewGroup) -> EventListView = {
            val view = LayoutInflater.from(it.context).inflate(layoutRes, it, false)
            HelloWorldViewImpl(view as ViewGroup)
        }
    }

    private val composeView: ComposeView = androidView.findViewById(R.id.composeView)

    init {
        composeView.setContent { Text(text = "Hi there!") }
    }

    override fun accept(vm: EventListView.ViewModel) {
    }
}
