package ru.gaket.ohmyevent.features.events.list

import androidx.lifecycle.Lifecycle
import com.badoo.mvicore.android.lifecycle.startStop
import com.badoo.ribs.core.Interactor
import com.badoo.ribs.core.builder.BuildParams
import io.reactivex.functions.Consumer

class HelloWorldInteractor(
    buildParams: BuildParams<Nothing?>,
    private val output: Consumer<EventListRib.Output>
) : Interactor<EventListRib, EventListView>(
    buildParams = buildParams
) {

    override fun onViewCreated(view: EventListView, viewLifecycle: Lifecycle) {
        viewLifecycle.startStop {
        }
    }
}
