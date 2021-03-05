# NY Times Most Popular
A simple app to hit the NY Times Most Popular Articles API and show a list of articles, that shows details when items on the list are clicked . 

<div align="center">
  <img src="https://user-images.githubusercontent.com/7644709/110174708-2fff5480-7e09-11eb-9234-6904a6f180d4.jpg" width="400px" /> 
  <img src="https://user-images.githubusercontent.com/7644709/110174771-4a393280-7e09-11eb-8011-6ba2642819b1.jpg" width="400px" />  
</div>





## Built With 🛠
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
  - [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) - in-app navigation
  - [DataBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
- [Dependency Injection](https://developer.android.com/training/dependency-injection)
  - [Dagger2](https://dagger.dev/) - Standard library to incorporate Dagger dependency injection into an Android application. **This is in a separate [dagger branch](https://github.com/wajahatkarim3/Imagine/tree/dagger-branch).**
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [Glide](https://github.com/bumptech/glide) - For Loading images from Urls.
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.
- Testing
  - [Unit Tests](https://en.wikipedia.org/wiki/Unit_testing) ([JUnit 5](https://junit.org/junit5/) via [android-junit5](https://github.com/mannodermaus/android-junit5))
  - [UT Tests](https://en.wikipedia.org/wiki/Graphical_user_interface_testing) ([Espresso](https://developer.android.com/training/testing/espresso))
  - [Mockk](https://mockk.io/) - mocking framework
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
  - Repository pattern
  - Clean Architecture approach.
![final-architecture](https://user-images.githubusercontent.com/7644709/94259993-b2691b80-ff2f-11ea-8bff-cc4ed3c8b6d9.png)

