footer: Squire Technologies
slidenumbers: true

# Making friends with JetPack Compose and MVI

### Or convenience of unidirectional flow

#### Artur Badretdinov

^ TODO: add contacts here

---

# Squire

![original fit](images/squire_home.png)
![original fit](images/squire_time.png)
![original fit](images/squire_details.png)

---

# Context

1. 2 people
1. 3 Months - from zero to published MVP with 20 screens
1. 6 months - 30 screens + improvements

---

# What are the main things composing an app?

---

# App's "layers"

1. Visuals on a Screen
1. Data on a Screen
1. Navigation between Screens
1. App's data and logic

---

# Stack

1. Visuals - Android Views
1. On-screen data - MviCore
1. Navigation - RIBs (Badoo's fork)
1. App's data - MviCore + Repositories

---

# Learnings

Pros
+ Great separation of concerns
+ Easier debug and testing
+ Clean interfaces

Cons
- A LOT of classes
- Ambiguity in terms of logic separation
- Overhead when creating simple things

^ Eg features vs interactors, etc. A learing curve is ok, not critical

---

# MVP - Success!

---

# What's next?

^ We're growing, btw, if you're interested in joining a skilled remote team with good perks - ping me

---

# Let's experiment!

---

> There are so many events!
-- Artur Badretinov, speaker

---

![fit 94%](images/eventbot.png)
![fit](images/events_list.jpg)

---

# Goals

1. Balance between modifiability and overhead
1. Produce testable code
1. Follow the Unidirectional flow
1. Low coupling between the four App's Components
1. Support 3-5 devs per project, long-term support

^ I know many people who're still using MVP wtih Moxy - and that's completely fine.
However, as we have this rare opportunity of working on a greenfield project - we should 
check out our options
Unidirectional is much simpler with Kotlin

---

# Visuals

---

# Goals

1. Less code
1. Better performance

^ View creation and binding...

---

# Visuals

1. Android Views
1. Jetpack Compose 
1. Facebook Litho
1. ~~Anko~~

^ Not so big choice, actually. The interesting thing is that the last items are declarative
^ As we're using MVI, it often adds some confusion that we need to have two parts of View -related logic:
initialization and binding. And Declarative helps us to go with only one

---

# Jetpack Compose

---

> Jetpack Compose is a modern toolkit designed to simplify UI development
-- Compose Codelab

---
[.build-lists: true]

# Promises

- Declarative UI
- Rethinking the legacy
- Independent from Android Version
- Compatible with Views

---
[.build-lists: true]

# Compose in a nutshell

1. Throw away layouts 
1. Throw away resource files
1. Do everything in code

---

# Hello world

```kotlin
// Old way
val textView = TextView(this)
textView.setText("Hello world")
setContentView(textView)
```

```kotlin
// New way
setContent { Text("Hello World") }
```

![fit right](images/helloworld.png)

---
# Functions?

```kotlin
// New way
setContent { Text("Hello World") }
```

^Do you remember what do the curly brackets mean? That's a function

---

# Why is it called Compose?

- Function calls a function
- One element composes others

---

# A typical screen

![fit left](images/scaffold.png)

---
[.code-highlight: all]

[.code-highlight: 1]
[.code-highlight: 2]
[.code-highlight: 4-6]
[.code-highlight: 7-10]
[.code-highlight: 11]
[.code-highlight: 12]
[.code-highlight: 13]

# A typical screen

```kotlin
val materialBlue700= Color(0xFF1976D2)
Scaffold(
    scaffoldState = ScaffoldState(DrawerState.Opened),
    topBar = { 
        TopAppBar(title = {Text("TopAppBar")},
           backgroundColor = materialBlue700)  },
    floatingActionButtonPosition = Scaffold.FabPosition.End,
    floatingActionButton = { FloatingActionButton() },
    drawerContent = { Drawer() },
    bodyContent = { Content() },
    bottomBar = { BottomAppBar() }
)
```

---

[.code-highlight: 1]
[.code-highlight: 3-6]

# Resources

Forget xml. Colors in ARGB!

```kotlin
val materialBlue700 = Color(0xFF1976D2)

body1 = TextStyle(
         fontFamily = FontFamily.SansSerif,
         fontWeight = FontWeight.Normal,
         fontSize = 16.sp
)
```

---

# Back to events

![fit right](images/events_list.jpg)

---

![fit ](images/loading.png)
![fit ](images/events_list.jpg)
![fit ](images/error.png)

---
[.code-highlight: all]
[.code-highlight: 1-4]
[.code-highlight: 6-12]
[.code-highlight: 9]
[.code-highlight: 10]
[.code-highlight: 11]

```kotlin
fun onCreate() {
    setContent {
       EventDetailsScreen(initialState)
    }
}

@Composable
fun EventDetailsScreen(state: ScreenState) {
  when (state) {
    is Loading -> EventDetailsLoading()
    is Error -> EventDetailsError(state.error)
    is Success -> EventDetails(state.event)
  }
}
```

---

```kotlin
@Composable
fun EventDetails(event: EventUiModel) {
    Scaffold(
        topBar = { DetailsTopAppBar(event) },
        bodyContent = { EventDisplay(event) }
    )
}

@Composable
private fun DetailsTopAppBar(event: EventUiModel) {
    TopAppBar(
        navigationIcon = { BackIcon() }
    )
}

@Composable
private fun EventDisplay(event: EventUiModel) {
 // ...
}
```

---

# A problem of state

Views with intrinsic state:
- `SwipeRefreshLayout`
- `Spinner`
- `EditText`

![right 80%](images/swipe_refresh.png)

---

# EditText with PhoneWatcher

```kotlin
phoneView.addTextChangedListener(phoneFormatter)
phoneView.addTextChangedListener(viewIntentSender)
// ...
fun update(vm : viewStateModel) {
  phoneView.setText(vm.phone)
}
```

![right 50%](images/phone_input.jpg)

^ selection will go nuts + text will become unformatted + infinite loop

---

# EditText in Compose

```kotlin
@Composable
fun PhoneTextField() {
  val state = state { TextFieldValue("") }
  TextField(
           value = state.value,
           onValueChange = { state.value = it }
  )
}
```

---

# EditText in Compose

```kotlin
@Composable
fun PhoneTextField(val text : String, 
                   val inputListener : Listener ) {
  TextField(
           value = text,
           onValueChange = { listener.textUpdated(it) }
  )
}
```

---
[.code-highlight: all]
[.code-highlight: 1,4]

# Recomposing

```kotlin
@Composable
fun PhoneTextField(val textStream : LiveData<String>, 
                   val inputListener : Listener ) {
  val state = stateOf { textStream.asState }
  TextField(
           value = text,
           onValueChange = { listener.textUpdated(it) }
  )
}
```
There are adapters for:
LiveData, ObservableSource, Flow

---
[.code-highlight: 2]
# Important note

```kotlin
@Composable
fun EventDetailsScreen(state: ScreenState) {
  when (state) {
    is Loading -> EventDetailsLoading()
    is Error -> EventDetailsError(state.error)
    is Success -> EventDetails(state.event)
  }
}
```

^ Every time we want to update a screen, we should call this function

---


#[fit]Where is my Event?

---

#[fit]~~Where is my Event?~~
#[fit] Where is my State?

---

# MVP

```kotlin
intervace View {
    fun showLoader()
    fun hideLoader()
    fun showError(msg: String)
    fun hideError()
    fun showContent(events: List<Event>)
}
```

---
[.code-highlight: all]
[.code-highlight: 3]

# MVVM

```kotlin
class View {
  init {
    loaderStream.observe { handleLoader(it) }
    errorStream.observe { handleError(it) }
    contentStream.observe { handleContent(it) }
  }
}
```

^ That's actually closer, as we can subscribe Compose to these streams

---
[.code-highlight: all]
[.code-highlight: 3]

# MVI

```kotlin
class View {
  init {
    uiStateSteam.observe { renderUi(it) }
  }
}
```

^Does it resemble something?

---

# MVI

![original 140%](images/MVI.png)

---

# Redux

1. Model (State)
1. View (View)
1. Intent (Action)

![fit](images/MVI-web.png)

^ That's came from Redux

---

![fit ](images/loading.png)
![fit ](images/events_list.jpg)
![fit ](images/error.png)

---

```kotlin
sealed class ScreenState<out T> {
  class Loading<out T> : ScreenState<T>()
  data class Error(val error: String) : ScreenState<Nothing>()
  class Success<out T>(val data: T) : ScreenState<T>()
}
```

---

# Content and update

```kotlin
data class DetailsState (
  val details: Event,
  val isLoading: Boolean,
  val errorMessage: String?
)
```

![fit right](images/list_and_update.jpg)

---

# ViewIntent

```kotlin
sealed class DetailsViewIntent {
  data class SetEvent(val id: String)
  object Refresh : DetailsViewIntent()
}
```

---
# High-level example

```kotlin
//create a store with an initial state
val store = Store<String>("Hello");
//when the store's state changes, update your UI
states(store).subscribe { uiModel -> renderUi(uiModel) }
//start dispatching actions to your store...
store.dispatch { oldState -> oldState + " World" }
```

---

# Why MVI?

1. Pushes you to care about the whole state
1. Testable
1. Things like State Logging and Time Machine

^ With this design, if you are tasked with resolving a bug, 
the first thing you’d probably do is to ensure that you have correct 
Actions and States flowing thru the system (you can utilize logs for that). 
If the Actions and States are what you expect, the next place to look would be the rendering logic

---

# Implementing MVI

---

# Alternatives for MVI

1. Manually
1. MVICore
1. MVIKotlin
1. Roxie
1. Mobius
1. RxRedux
1. Mosby

^ https://github.com/ww-tech/roxie
https://github.com/spotify/mobius
- MVICore  doesn't handle orientation changes
MVIKotlin handles it
MVICore - full-blown framework, every component is pure. RxJava2 only.
MVIKotlin - less purity, 3 components: Executor (actor, post processor + publisher). 
Some notes of imperativity. More generic. Coroutines/Flow, Reaktive - pluggable.
Mobius - Java

---
# What to pay attention at?

1. Language - Java 7/8 or Kotlin
1. Process death / Configuration change
1. Plugin - based architecture
1. Ability to select a reactive framework
1. Overall simplicity of code

---
# Our experience

1. Kotlin + Big team -> MVICore
1. Kotlin Multiplatform / flexible settings -> MVIKotlin
1. Lightweight solution - Roxie
1. Own bicycle - not so bad :)

---

# Navigation and DI

---
# Goals

1. Being able to go back and forth (backstack)
1. Convenient DI
1. Composable
1. Lifecycle. Ideally, a simple one

---
# Options

1. Fragments + Fragment Manager, Cicerone / Nav Components
1. Badoo Ribs
1. Decompose
1. Conductor
1. Square Workflow
1. Navigation Feature in MainActivity
1. ~~Flow~~
1. ~~Activities~~


^ In theory, Cicerone is not tied to fragments . Here, tell in details about every item
---
# Badoo Ribs

1. Proper DI
1. Simpler lifecycle

---
# Navigation Feature

1. No new dependencies
1. Or... ComposeRouter library :)

---
# Our thoughts

1. Common solution - Fragments
1. Cleaner contracts + better DI - Badoo Ribs
1. Feeling Hipster - Jetpack-only navigation / ComposerRouter

---

#[fit] To conclude

---

# Oh My Event [WIP]

1. Jetpack Compose
1. Custom MVI
1. Ribs

^ Btw, don't mess up RIP and WIP :)

---
[.build-lists: true]

# Pros 

1. Code-first approach
1. Code navigation and Single Responsibility
1. Independence from Android Updates
1. Simpler RecyclerViews
1. Ability to change every layer independently
1. Balance between simplicity and modifiability

^ 1. With Compose, your UI is defined as a function, and this function transforms data into View Hierarchy. As a result, we don't need to separate View initialization and its further update - it's in the same place. Add image of a 3-button-button
^ 4. We can literally create everything in code... conveniently. Get rid of millions of shapes with different colors or different corners

---
[.build-lists: true]

# Cons

1. Learning curve
2. Readability
3. Need to mature

^ I know many devs who are ok with the way things already work

--- 

# Jetpack Compose Plans 

1. Biweekly releases
2. Alpha is something that we can play with in our apps
3. Release the 1.0 in 2021
4. Definitely early to use in prod, good to use in side projects, if ok with refactoring it later

---

#[fit] Thank you for your time

---

# Links

Events App repo: 
[https://github.com/Gaket/OhMyEvent](https://github.com/Gaket/OhMyEvent)
Main examples: 
[https://github.com/android/compose-samples](https://github.com/android/compose-samples)
Android Academy: 
[https://www.youtube.com/channel/UCmkVThwbjthEg2RR4iuDQWA](https://www.youtube.com/channel/UCmkVThwbjthEg2RR4iuDQWA)

https://github.com/square/workflow-kotlin
https://github.com/airbnb/MvRx
https://github.com/zsoltk/compose-router

---

# Backup slides

---



# Modifiers

```kotlin
Image(
    modifier = Modifier
        .clickable(onClick = onAuthorClick)
        .padding(horizontal = 16.dp)
        .preferredSize(42.dp)
        .border(1.5.dp, borderColor, CircleShape)
        .border(3.dp, MaterialTheme.colors.surface, CircleShape)
        .clip(CircleShape)
        .gravity(Alignment.Top),
    asset = image,
    contentScale = ContentScale.Crop
)
```
![right fit 200%](images/avatar.png)

^ Initially they were the same compose functions, but it created too much nesting

---

[.code-highlight: all]
[.code-highlight: all]
[.code-highlight: 4]
[.code-highlight: 6]
[.code-highlight: 8]

# Modifiers

```kotlin
Image(
    modifier = Modifier
        .clickable(onClick = onAuthorClick)
        .padding(horizontal = 16.dp)
        .preferredSize(42.dp)
        .border(1.5.dp, borderColor, CircleShape)
        .border(3.dp, MaterialTheme.colors.surface, CircleShape)
        .clip(CircleShape)
        .gravity(Alignment.Top),
    asset = image,
    contentScale = ContentScale.Crop
)
```

^ Initially they were the same compose functions, but it created too much nesting

---


About Ribs:


RIBs support Jetpack
ribs - normal DI

Decompose - minimalistic RIBS - DI + navigation - experiment
Portals in RIBS - not a full-screen rib that we want to open a full screen from it

- mvicore - check out time capsule + saved state registry

??? - overlay / push

---