package ru.gaket.ohmyevent.data

import android.content.Context
import ru.gaket.ohmyevent.models.Event
import kotlin.random.Random

class EventsProvider(val context: Context) {

    val events: List<Event> = allEvents()
        get() {
            val start = Random.nextInt(field.size)
            val end = Random.nextInt(7, 10)
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
                "Production Debugging for Everyday Programmer",
                "Production Debugging for Everyday Programmer from GDG San Francisco. This talk tells about how bigger companies like Uber use observability and development principles to untangle complex production code and to keep the system running even when thousands of developers work with ample of individual microservices.",
                "Production Debugging for Everyday Programmer. How to keep the system running even when thousands of developers work with ample of individual microservices.",
                "https://www.meetup.com/ru-RU/GDGSanFrancisco/events/272383358/",
                listOf("Android", "Online", "Free"),
                null
            ),
            Event(
                "4",
                "It's OK to be different",
                "Having different ideas, opinions and interests can be quite lonely and lead to thinking about where you can fit, especially in the fast-paced tech industry. Coming to the industry, not by a traditional path, being the only one in the room to have a unique opinion can be very intimidating. Being unique can put us in a position where we think that we don't belong here.",
                "Having different ideas, opinions and interests can be quite lonely and lead to thinking about where you can fit, especially in the fast-paced tech industry.",
                "https://www.meetup.com/ru-RU/GDGSanFrancisco/events/272406934/",
                listOf("Android", "Online", "Free"),
                null
            ),
            Event(
                "5",
                "Android 11 #5 - Making friends with Jetpack Compose: MVI and Animation",
                "Jetpack Compose is an entirely new way of doing declarative UI. We are here to help you understand what that beast and how to tame it is. In short:- Jetpack Compose Basics- Redux-Like state management with Jetpack Compose- Animations with Jetpack Compose",
                "Jetpack Compose Basics- Redux-Like state management with Jetpack Compose- Animations with Jetpack Compose",
                "[https://www.eventbrite.com/e/android-11-5-making-friends-with-jetpack-compose-mvi-and-animation-tickets-117403953385](https://www.eventbrite.com/e/android-11-5-making-friends-with-jetpack-compose-mvi-and-animation-tickets-117403953385)",
                listOf("Android", "Online", "Free"),
                null
            ),
            Event(
                "6",
                "World Blockchain Hackathon - The Babylon Project",
                "Hi GDG community, we have a super exciting event coming up: The Babylon Project - a 3-days virtual Blockchain Hackathon!",
                "3-days virtual Blockchain Hackathon!",
                "https://www.meetup.com/ru-RU/GDG-Cloud-Santa-Clara/events/272563971/",
                listOf("Blockchain", "Online", "Free"),
                null
            ),
            Event(
                "7",
                "Android 11 #4 - Android Navigation Component and CameraX",
                "Two years ago on Google I/O we've been introduced to the new solution for handling navigation in Android apps — Jetpack Navigation Component library. There are a lot of talks and articles about small apps and tiny examples, but almost no information about problems you may encounter if you want to transfer your really big app's routing logic on Navigation Component. Pavel will show you several cases which can cause some problem.                        CameraX is                for the rescue !Simplified,                unified and predictable API . All complexities are hidden under the hood,                without headaches .                ",
                "Pavel will show you several cases which can cause some problem.                        CameraX is                for the rescue !Simplified,                 unified and predictable API . All complexities are hidden under the hood,                without headaches .                ",
                "[https://www.eventbrite.com/e/android-11-4-android-navigation-component-and-camerax-tickets-115646777625#](https://www.eventbrite.com/e/android-11-4-android-navigation-component-and-camerax-tickets-115646777625#)",
                listOf("Android", "Online", "Free"),
                null
            ),
            Event(
                "8",
                "Discuss on coding interview questions",
                "Let's do the mock interviews!",
                "Let's do the mock interviews!",
                "[https://www.meetup.com/ru-RU/iBridge-Interview-Preparation-Group/events/zwqmrrybcmbhb/](https://www.meetup.com/ru-RU/iBridge-Interview-Preparation-Group/events/zwqmrrybcmbhb/)",
                listOf("Android", "Free"),
                null
            )
        )
    }


}