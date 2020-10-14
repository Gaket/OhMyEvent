footer: Android Academy
slidenumbers: true

# Making friends with JetPack Compose and MVI

### Or convenience of unidirectional flow

---

> There are so many events!
-- Artur Badretinov, speaker

---

![fit 94%](images/eventbot.png)
![fit](images/events_list.jpg)

---
# Custom views

```kotlin
if (event.isCancelled) {
  event.setTextColorResource(...)
  event.setTypeface(...))
  event.text = getString(...)
} else {
  event.setTextColorResource(...)
  event.setTypeface(...))
  event.text = getString(...)
}
```

![right fit](images/custom_rows.jpg)

^ And that's not the worst case - an example of a custom view 

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

# Existing elements replacements

- TextView, EditText, ImageView ...
- FrameLayout, LinearLayout ...
- ConstraintLayout, RecyclerView, ...

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
#[fit]Where is my State?

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

Looks better:

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
[.code-highlight: 1, 5]

# MVI

```kotlin
fun EventDetailsScreen(state: ScreenState)

class View {
  init {
    uiStateSteam.observe { EventDetailsScreen(it) }
  }
}
```

---

#[fit] Model View Intent

---

> Model is responsible for representing state, structure, and behaviour of the user’s mental model.

---

> A View presents information that it retrieves from one or more model objects.
>-- Trygve Reenskaug in 1979 as a part of MVC architecture.

^ Loosely speaking, the original explanation specifies Model as an entity which tells View what to display on the screen. If Model changes, View gets notified about the change, and it will render the change on the screen.

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

# What's next?

1. Architecture
1. Navigation
1. DI

^Parts of a prod app

---

# Architecture

No restrictions:

1. Mutliple Activities
1. Multiple Fragments
1. Compose - only

^ Do you remember how apps architecture changed when Android Views system came?

---

# Navigation

No restrictions:

1. Jetpack Navigation - in progress
1. Custom routers (example in JetNews)

---

#[fit] To conclude

---
[.build-lists: true]

# Pros 

1. Code-first approach
1. Code coherence
1. Code navigation
1. Independence from Android Updates
1. Simpler RecyclerViews

^ 1. With Compose, your UI is defined as a function, and this function transforms data into View Hierarchy. As a result, we don't need to separate View initialization and its further update - it's in the same place. Add image of a 3-button-button
^ 4. We can literally create everything in code... conveniently. Get rid of millions of shapes with different colors or different corners

---
[.build-lists: true]

# Cons

1. Learning curve
2. Readability
3. Need to mature
4. Navigation without classes 😱

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

