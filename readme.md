# ToDo App

This Android Studio project is a task management application built using the MVVM architecture and various modern Android development tools. It allows users to view, manage, and create tasks.

## Architecture

* **MVVM (Model-View-ViewModel):** The app follows the Model-View-ViewModel architecture pattern, promoting separation of concerns and testability.
* **Single Activity Architecture:** The application uses a single activity with multiple fragments managed by the Navigation component.

## Features

* Task listing with details.
* Display of task counts (total, pending, ongoing, completed).
* Network task fetching and local storage using Room Database.
* Swipe-to-delete functionality.
* Search functionality.
* Task addition and local storage.
* Google Analytics event tracking.
* Firebase Crashlytics and Performance Monitoring.
* Lottie animations for no data found, and loading states.

## Dependencies

* **Dagger Hilt:** Dependency injection for managing dependencies.
* **Room Database:** Local data persistence.
* **Navigation Component:** Navigation between fragments.
* **Coroutines:** Asynchronous programming.
* **ViewModels:** Managing UI-related data.
* **Retrofit:** Network communication.
* **Firebase (Analytics, Crashlytics, Performance):** Analytics, crash reporting, and performance monitoring.
* **Lottie:** Animations.
* **Data Binding:** Binding UI components to data sources.
* **LiveData:** Observable data holder class.
* **Material UI:** Material Design components.
* **RecyclerView:** Displaying lists of data.

## Setup

1.  **Clone the Repository:**
    ```bash
    git clone <repository_url>
    ```
2.  **Open in Android Studio:**
    * Open Android Studio and select "Open an existing Android Studio project."
    * Navigate to the cloned repository and select the project directory.
3.  **Firebase Setup:**
    * Create a Firebase project on the Firebase console.
    * Add an Android app to your Firebase project.
    * Download the `google-services.json` file and place it in your app module's root directory.
    * Initialize Firebase in the `Application` class:
        ```kotlin
        import com.google.firebase.FirebaseApp
        //... inside Application.onCreate()
        FirebaseApp.initializeApp(this)
        ```
4.  **Gradle Sync:**
    * Sync the Gradle files in Android Studio.
5.  **Build and Run:**
    * Build and run the application on an emulator or physical device.

## Project Structure

app/
├── data/
│   ├── localDB/          # Room Database entities and DAOs
│   ├── models/       # Data models     
│   ├── network/      # Retrofit API interfaces
│   └── repositories/ # Data repositories
├── di/             # Dagger Hilt modules
├── ui/
│   ├── home/    # Dashboard fragment (task listing)
│   ├── addtask/     # add task fragment
│   ├── settisearchngs/    # search fragment
│   └── main/         # Main activity
├── viewmodels/     # ViewModels
└── MainApplication.kt # Application class


## Implementation Details

* **Room Database and Retrofit:**
    * Singleton classes are used to provide instances of the Room Database and Retrofit.
    * API interfaces define the endpoints for network requests.
    * Entities represent the data models for the database.
    * DAOs provide methods for database operations.
* **Navigation:**
    * The Navigation component is used to manage navigation between fragments.
    * A navigation graph defines the navigation flow.
* **ViewModels and LiveData:**
    * ViewModels are used to manage UI-related data.
    * LiveData is used to observe data changes and update the UI.
* **Data Binding:**
    * Data binding is used to bind UI components to data sources, reducing boilerplate code.
* **Google Analytics:**
    * Google Analytic events are logged through firebase analytics.
* **Firebase Crashlytics and Performance Monitoring:**
    * Firebase Crashlytics is used for crash reporting.
    * Firebase Performance Monitoring is used to monitor app performance.
* **Lottie Animations:**
    * Lottie animations are used for loading states and empty states.
* **Search and Delete:**
    * Search functionality is implemented using RecyclerView's filter functionality.
    * Swipe-to-delete is implemented using ItemTouchHelper.
* **Task Addition:**
    * Tasks are added and stored in the local Room database.
* **Dummy API:**
    * A dummy API is used for network requests.

Video : https://drive.google.com/file/d/13merCIwWfOWEMgLzHXDM0nJ_j7SyKAcp/view?usp=sharing
Images : https://drive.google.com/file/d/14_kjYj146jLixeU9RsYyGE_CbT0VhEPs/view?usp=sharing
Images : https://drive.google.com/file/d/14McCqz0W7lV0nA7sAw4opAazbYW0dUM1/view?usp=sharing
Images : https://drive.google.com/file/d/14McCqz0W7lV0nA7sAw4opAazbYW0dUM1/view?usp=sharing
Images : https://drive.google.com/file/d/145jckfOXkaO2uzpuskFQRJcQeIY0-cRt/view?usp=sharing
Images : https://drive.google.com/file/d/14VC0VZea348P0NaRDlxA8G_TNfQh_I-1/view?usp=sharing
Images : https://drive.google.com/file/d/1Zd5fGIhqpareuhHtkiiansgYVUQmSQ58/view?usp=sharing
Images : https://drive.google.com/file/d/1L6JoQkQQn1kXfti6nYzKy22XRfyfk0I9/view?usp=sharing
Images : https://drive.google.com/file/d/1WyBaOcKFodv7RL3nk8Qd0zz_zZmnofi1/view?usp=sharing
Images : https://drive.google.com/file/d/1_kQCU1NmbSLtNRfHEmAz4cmez6Uc-FOl/view?usp=sharing
app link : https://drive.google.com/file/d/1i17yHDobKcOEQ61dB31_Gr2NFVvl62vS/view?usp=sharing

