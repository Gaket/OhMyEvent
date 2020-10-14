package ru.gaket.ohmyevent.features.events.list

import com.badoo.ribs.core.Rib
import com.badoo.ribs.core.view.ViewFactory
import com.badoo.ribs.customisation.inflateOnDemand
import io.reactivex.functions.Consumer

interface EventListRib : Rib {

    interface Dependency {
        fun helloWorldOutput(): Consumer<Output>
    }

    sealed class Output {
        object HelloThere : Output()
    }
}