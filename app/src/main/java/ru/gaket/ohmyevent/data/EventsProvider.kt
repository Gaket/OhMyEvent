package ru.gaket.ohmyevent.data

import android.content.Context
import ru.gaket.ohmyevent.models.Event
import kotlin.random.Random

class EventsProvider(val context: Context) {

    val events: List<Event> = allEvents()
        get() {
            val start = Random.nextInt(field.size)
            val end = Random.nextInt(3, 10)
            return field.subList(start, minOf(field.size - 1, start + end))
        }

    private fun allEvents(): List<Event> {
        return listOf(
            Event(
                "1",
                "Android Academy 11",
                "Let's see what's going on with Android 11!",
                "Let's see what's going on with Android 11!",
                "https://www.eventbrite.com/myevent?eid=117403953385",
                listOf("Android", "Online", "Free"),
                null
            ),
            Event(
                "2",
                "droidcon Italy 2020",
                "A two day conference with presentations, workshops, and an interactive exhibition, droidcon brings together 1000+ Android developers, designers and project managers to learn, share and network.",
                "Europe's Largest Android Conference",
                "https://www.eventbrite.it/e/droidcon-italy-2020-europes-largest-android-conference-tickets-67032075743?aff=ebdssbdestsearch",
                listOf("Android", "Paid"),
                null
            ),
            Event(
                "3",
                "Android Manifest | Meetup #4",
                "In cooperation with Magnus HR, Android Manifest Armenia aims to connect or reconnect with Mobile engineers and tech enthusiasts. We have designed one-day meetup series to revolve around the conversations and networking opportunities for fellow #androiders that are eager to develop a strong career through Android development.",
                "Android Manifest Armenia aims to connect or reconnect with Mobile engineers and tech enthusiasts.",
                "https://www.eventbrite.it/e/droidcon-italy-2020-europes-largest-android-conference-tickets-67032075743?aff=ebdssbdestsearch",
                listOf("Android", "Free"),
                null
            ),
            Event(
                "4",
                "Android Academy 12",
                "Let's see what's going on with Android 11!",
                "Let's see what's going on with Android 11!",
                "https://www.eventbrite.com/myevent?eid=117403953385",
                listOf("Android", "Online", "Free"),
                null
            ),
            Event(
                "5",
                "droidcon Moscow 2020",
                "A two day conference with presentations, workshops, and an interactive exhibition, droidcon brings together 1000+ Android developers, designers and project managers to learn, share and network.",
                "Europe's Largest Android Conference",
                "https://www.eventbrite.it/e/droidcon-italy-2020-europes-largest-android-conference-tickets-67032075743?aff=ebdssbdestsearch",
                listOf("Android", "Paid"),
                null
            ),
            Event(
                "6",
                "Android Auto | Meetup #4",
                "In cooperation with Magnus HR, Android Manifest Armenia aims to connect or reconnect with Mobile engineers and tech enthusiasts. We have designed one-day meetup series to revolve around the conversations and networking opportunities for fellow #androiders that are eager to develop a strong career through Android development.",
                "Android Manifest Armenia aims to connect or reconnect with Mobile engineers and tech enthusiasts.",
                "https://www.eventbrite.it/e/droidcon-italy-2020-europes-largest-android-conference-tickets-67032075743?aff=ebdssbdestsearch",
                listOf("Android", "Free"),
                null
            ),
            Event(
                "7",
                "Android Academy 14",
                "Let's see what's going on with Android 11!",
                "Let's see what's going on with Android 11!",
                "https://www.eventbrite.com/myevent?eid=117403953385",
                listOf("Android", "Online", "Free"),
                null
            ),
            Event(
                "8",
                "droidcon Prague 2020",
                "A two day conference with presentations, workshops, and an interactive exhibition, droidcon brings together 1000+ Android developers, designers and project managers to learn, share and network.",
                "Europe's Largest Android Conference",
                "https://www.eventbrite.it/e/droidcon-italy-2020-europes-largest-android-conference-tickets-67032075743?aff=ebdssbdestsearch",
                listOf("Android", "Paid"),
                null
            ),
            Event(
                "9",
                "Android Broadcast | Meetup #4",
                "In cooperation with Magnus HR, Android Manifest Armenia aims to connect or reconnect with Mobile engineers and tech enthusiasts. We have designed one-day meetup series to revolve around the conversations and networking opportunities for fellow #androiders that are eager to develop a strong career through Android development.",
                "Android Manifest Armenia aims to connect or reconnect with Mobile engineers and tech enthusiasts.",
                "https://www.eventbrite.it/e/droidcon-italy-2020-europes-largest-android-conference-tickets-67032075743?aff=ebdssbdestsearch",
                listOf("Android", "Free"),
                null
            ),
            Event(
                "10",
                "Android Podcast 11",
                "Let's see what's going on with Android 11!",
                "Let's see what's going on with Android 11!",
                "https://www.eventbrite.com/myevent?eid=117403953385",
                listOf("Android", "Online", "Free"),
                null
            ),
            Event(
                "11",
                "droidcon SF 2020",
                "A two day conference with presentations, workshops, and an interactive exhibition, droidcon brings together 1000+ Android developers, designers and project managers to learn, share and network.",
                "Europe's Largest Android Conference",
                "https://www.eventbrite.it/e/droidcon-italy-2020-europes-largest-android-conference-tickets-67032075743?aff=ebdssbdestsearch",
                listOf("Android", "Paid"),
                null
            ),
            Event(
                "12",
                "Android Manifest | Meetup #4",
                "In cooperation with Magnus HR, Android Manifest Armenia aims to connect or reconnect with Mobile engineers and tech enthusiasts. We have designed one-day meetup series to revolve around the conversations and networking opportunities for fellow #androiders that are eager to develop a strong career through Android development.",
                "Android Manifest Armenia aims to connect or reconnect with Mobile engineers and tech enthusiasts.",
                "https://www.eventbrite.it/e/droidcon-italy-2020-europes-largest-android-conference-tickets-67032075743?aff=ebdssbdestsearch",
                listOf("Android", "Free"),
                null
            )
        )
    }


}