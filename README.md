# NYTimesMostPopular

 An android app built using Kotlin that consumes [Ney York Times API](https://developer.nytimes.com/get-started/3) to display current popular articles.
It has been built following Architecture Principle, Repository Pattern, MVVM Architecture in the presentation layer as well as jetpack components.
### Prerequisites

#### Ney York Times API Key.

The key needs to be added to the `local.properties` file, so that it's read as a build config value. A better way to save the keys would be by using the [Google Secret Gradle Plugin](https://github.com/google/secrets-gradle-plugin)

```yaml
API_KEY="*******************"
```
### Libraries.

- [Hilt](https://github.com/google/hilt) - Dependency Injection library.
- [Jetpack](https://developer.android.com/jetpack)
  -   [Android KTX](https://developer.android.com/kotlin/ktx.html) - Provide concise, idiomatic Kotlin to Jetpack and Android platform APIs.
    - [AndroidX](https://developer.android.com/jetpack/androidx) - Major improvement to the original Android [Support Library](https://developer.android.com/topic/libraries/support-library/index), which is no longer maintained.
    -   [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - Perform actions in response to a change in the lifecycle status of another component, such as activities and fragments.
    -   [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
    - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services.
    -   [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Designed to store and manage UI-related data in a lifecycle conscious way. The ViewModel class allows data to survive configuration changes such as screen rotations.
     - [Data Binding](https://developer.android.com/topic/libraries/data-binding/) - Allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
    - [Room](https://developer.android.com/training/data-storage/room) - Provides an abstraction layer over SQLite used for offline data caching.
    - [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started)-Component that allows easier implementation of navigation from simple button clicks to more complex patterns.

  
- [Retrofit](https://square.github.io/retrofit/) - To make network request.
-  Tests
    - [JUnit5](https://junit.org/junit5/)
    - [Mockito](https://github.com/mockito/mockito)
    - [Turbine](https://github.com/cashapp/turbine)

- CI/CD
    - Github Actions

### Screenshots

I added some screenshots in the `screenshots` folder, in the root directory of the project. Added some GIFs to also show end to end test on the app


<table>
<thead>
<tr>
<th>Mode</th>
<th>Home screen</th>
<th>Article Details Screen</th>
<th>Read More</th>
</tr>
</thead>
<tbody>
<tr>
<td>Light</td>
<td><a target="_blank" rel="noopener noreferrer" href="https://github.com/paulnjoroge789016/NYTimesMostPopular/blob/master/screenshots/home.png"><img src="https://github.com/paulnjoroge789016/NYTimesMostPopular/blob/master/screenshots/home.png" style="max-width: 100%;" width="250"></a></td>
<td><a target="_blank" rel="noopener noreferrer" href="https://github.com/paulnjoroge789016/NYTimesMostPopular/blob/master/screenshots/article_details.png"><img src="https://github.com/paulnjoroge789016/NYTimesMostPopular/blob/master/screenshots/article_details.png" style="max-width: 100%;" width="250"></a></td>
<td><a target="_blank" rel="noopener noreferrer" href="https://github.com/paulnjoroge789016/NYTimesMostPopular/blob/master/screenshots/read_more.png"><img src="https://github.com/paulnjoroge789016/NYTimesMostPopular/blob/master/screenshots/read_more.png" style="max-width: 100%;" width="250"></a></td>
</tr>
<tr>
 <td>On Error</td>
 <td><a target="_blank" rel="noopener noreferrer" href="https://github.com/paulnjoroge789016/NYTimesMostPopular/blob/master/screenshots/on_error.png"><img src="https://github.com/paulnjoroge789016/NYTimesMostPopular/blob/master/screenshots/on_error.png" style="max-width: 100%;" width="250"></a></td>
</tr>
</tbody>
</table>

## Architecture.

### What is Clean Architecture?

A well planned architecture is extremely important for an app to scale and all architectures have one common goal- to manage complexity of your app. This isn't something to be worried about in smaller apps however it may prove very useful when working on apps with longer development lifecycle and a bigger team.

Clean architecture was proposed by [Robert C. Martin](https://en.wikipedia.org/wiki/Robert_C._Martin) in 2012 in the [Clean Code Blog](http://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) and it follow the SOLID principle.

<p align="center"><img src="screenshots/clean_arch.png" alt="Clean Architecture Diagram"></p>

The circles represent different layers of your app. Note that:

- The center circle is the most abstract, and the outer circle is the most concrete. This is called the [Abstraction Principle](https://en.wikipedia.org/wiki/Abstraction_principle_(computer_programming)). The Abstraction Principle specifies that inner circles should contain business logic, and outer circles should contain implementation details.

- Another principle of Clean Architecture is the [Dependency Inversion](https://en.wikipedia.org/wiki/Dependency_inversion_principle). This rule specifies that each circle can depend only on the nearest inward circle ie. low-level modules do not depend on high-level modules but the other way around.

### Why Clean Architecture?
- Loose coupling between the code - The code can easily be modified without affecting any or a large part of the app's codebase.
- Easier to test code.
- Separation of Concern - Different modules have specific responsibilities making it easier for modification and maintenance.
