# Breaking Bad Applicatoin
This is the Braking Bad application for Android Platform.

## Getting started
This application is written using Kotlin v.1.4.10 agains Android Studio 4.1.1 with Android Gradle Plugin v. 4.1.1


In this project you will find:
The app is built using the API provided by https://breakingbadapi.com/
The codebase is in Kotlin
The project is following the MVVM architecture pattern, using Android Architecture Components
The project is following Material Design
Dagger 2 is used as a dependency injection framework
A combination of RxJava2, Retrofit 2 and OkHttp4 for implementing the network infrastructure
Working to be lint free! Currently Android Linting has many offenders but new contributions should help progress towards zero.
Opening the project in Android Studio
To open the project in Android Studio, begin by unziping the file. After that open the root directory in Android Studio.

## Verifying Installation
to verify project installation and configuration were successful, build the applicatoin from the command line:

```
gradlew :app:assembleDebug
```

to run unit tests:
```
gradlew :app:test
```

to create a debug build with with unit & automation tests and also lint test
```
gradlew assembleDebug lintDebug testDebugUnitTest --refresh-dependencies
```
## Testing
The project contains both JVM-based unit test. Unit tests focus on both Repository unit testing. Using AndroidX Test and Mockito mocking framework. 

## 3rd party libraries
Dagger 2 - DI framework
GSON - JSON parser
OKHttp, Okio, Retrofit, RXAdapters - Network facade
RxJava 2 - Library for composing asynchronous and event-based programs by using observable sequences
Google Maps
Lottie - Animation Library
Mockito - Mocking framework
OKHttp Idler - Idle Resource wrapper around OKHTTP client
Aditional feature
Screen rotation, portrait to landscape and vice versa
Dark mode


