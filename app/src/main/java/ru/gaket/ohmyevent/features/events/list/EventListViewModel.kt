package ru.gaket.ohmyevent.features.events.list

import androidx.lifecycle.*
import com.example.androidacademy.sections.list.ScreenState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import ru.gaket.ohmyevent.data.EventsRepository
import ru.gaket.ohmyevent.features.events.EventUiModel

class EventListViewModel(private val repository: EventsRepository) : ViewModel() {

    private val _eventsListState = MutableLiveData<ScreenState<List<EventUiModel>>>()
    val eventsListState: LiveData<ScreenState<List<EventUiModel>>>
        get() = _eventsListState

    init {
        loadEvents()
    }

    private fun loadEvents() {
        repository.getEvents()
            .subscribeOn(Schedulers.io())
            .flatMapIterable { event -> event }
            .map { EventUiModel.from(it) }
            .toList()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _eventsListState.value = ScreenState.Loading() }
            .doOnError { _eventsListState.value = ScreenState.Error("Can't download events") }
            .subscribe(Consumer {
                _eventsListState.value = ScreenState.Success(it)
            })
    }
}

class UserViewModelFactory : ViewModelProvider.Factory {

    lateinit var eventsRepository: EventsRepository

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EventListViewModel(eventsRepository) as T
    }
}