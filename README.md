# MyNotesApplication

A modern, lightweight Android Notes application built with **Kotlin** and **Jetpack Compose**. This app demonstrates a complete CRUD (Create, Read, Update, Delete) workflow with persistent local storage.

## ✨ Features

- **List Notes**: View all your saved notes in a clean, scrollable list.
- **Add Note**: Create new notes with a title and content.
- **Edit Note**: Modify existing notes by tapping on them.
- **Delete Note**: Remove unwanted notes with a single tap.
- **Local Persistence**: All data is saved locally on your device using **Room Database**, so your notes are safe even if the app is closed.

## 🛠️ Tech Stack

- **Language**: [Kotlin](https://kotlinlang.org/)
- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose) (Modern Android UI toolkit)
- **Architecture**: MVVM (Model-View-ViewModel)
- **Database**: [Room](https://developer.android.com/training/data-storage/room) (SQLite abstraction layer)
- **Navigation**: [Jetpack Navigation Compose](https://developer.android.com/jetpack/compose/navigation)
- **Dependency Implementation**:
    - **ViewModel & LiveData**: For state management.
    - **Coroutines & Flow**: For asynchronous data handling.
    - **KSP**: For faster Room compilation.

## 📁 Project Structure

```text
app/src/main/java/com/example/mynotesapplication/
├── data/               # Data layer (Room Repository, Entity, DAO)
├── ui/                 # UI layer (Screens, ViewModel, Theme)
└── MainActivity.kt     # App entry point and Navigation setup
```

## 🚀 How to Run

1. Clone or open this project in **Android Studio (Ladybug or newer recommended)**.
2. Sync the project with Gradle files.
3. Run the app on an **Android Emulator** or a physical device (API 24+).

## 📝 License

This project is for educational purposes as a template for Android development.
