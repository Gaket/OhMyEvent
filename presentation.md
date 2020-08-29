footer: Android Academy
slidenumbers: true

# Making friends with JetPack Compose and MVI

### Or convenience of unidirectional flow

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

# Compose in a nutshell

Throw away layouts (now) and all the resource files (in the future),
create and control your material views in code.

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

---

# Why Compose?

- Function calls a function
- One element composes others

---

# A typical screen

```kotlin
val materialBlue700= Color(0xFF1976D2)
Scaffold(
       scaffoldState = ScaffoldState(DrawerState.Opened),
       topBar = { TopAppBar(title = {Text("TopAppBar")},backgroundColor = materialBlue700)  },
       floatingActionButtonPosition = Scaffold.FabPosition.End,
       floatingActionButton = { FloatingActionButton(onClick = {}){
           Text("X")
       } },
       drawerContent = { Drawer() },
       bodyContent = { Content() },
       bottomBar = { BottomAppBar(backgroundColor = materialBlue700) { Text("BottomAppBar") } }
   )
```

---

# Resources

Forget xml:
```kotlin
val materialBlue700= Color(0xFF1976D2)
```

---

# Modifiers

// TODO: example with paddings etc

^ Initially they were the same compose functions, but it created too much nesting
---
---
# MVI
---

> “Model is responsible for representing state, structure, and behaviour of the user’s mental model.”
>  “A View presents information that it retrieves from one or more model objects.”
>  “Let the View register with the Model as being a dependent of the Model, and let the Model send appropriate messages to its dependents whenever it changes. “
-- Trygve Reenskaug in 1979 as a part of MVC architecture.

^ Loosely speaking, the original explanation specifies Model as an entity which tells View what to display on the screen. If Model changes, View gets notified about the change, and it will render the change on the screen.

---
---
---
---
---
---