package ru.gaket.ohmyevent.data

import io.reactivex.Observable
import ru.gaket.ohmyevent.models.Event
import java.lang.RuntimeException
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class EventsRepository(val eventsProvider: EventsProvider) {

    fun getEvents(): Observable<List<Event>> {
        return Observable.timer(3, TimeUnit.SECONDS)
            .flatMap {
                val throwRandomError = Random.nextInt() % 3 == 1
                if (throwRandomError) {
                    Observable.error(RuntimeException("Smth went wrong"))
                } else {
                    Observable.fromCallable { eventsProvider.events }
                }
            }
    }
}