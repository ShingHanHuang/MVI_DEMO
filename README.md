# mvi_demo

mvi_demo is an Android sample project that demonstrates how to implement an application using the MVI (Model-View-Intent) architecture, Koin for dependency injection, Retrofit with OkHttp and Gson for network communication, and Kotlin Coroutines for asynchronous operations.

## Features

- **MVI Architecture**  
  Implements unidirectional data flow by separating UI intents, states, and one-time effects for improved maintainability and testability.

- **Dependency Injection with Koin**  
  Uses [Koin](https://insert-koin.io/) to manage dependencies across the app, including ViewModels, repositories, adapters, and network services.

- **Network Requests**  
  Uses Retrofit and OkHttp to perform API calls. JSON responses are parsed using Gson. The project utilizes suspend functions and a custom API call wrapper (`safeApiCall`) to handle errors and responses uniformly.

- **ViewBinding**  
  Enabled ViewBinding to reduce boilerplate code and improve type safety when accessing views.

## Project Structure

- **api/**  
  Contains Retrofit API interfaces and data model classes (e.g., `HttpResponse`, `CarParkDetailResponse`).

- **data/**  
  Contains data classes like `CarParkInfo` and `UserInfo`.

- **intent/**  
  Defines UI intents, states, and effects as per the MVI design pattern.

- **repository/**  
  Implements a `BaseRepository` with a common API call wrapper and concrete repositories (e.g., `CarParkInfoRepository`) that handle network requests and error handling.

- **viewmodel/**  
  Contains ViewModel classes that use the `viewModelScope` to make API calls and update the UI state.

- **adapter/**  
  RecyclerView adapters (e.g., `CarParkInfoAdapter`) for displaying data in lists.

- **koin/**  
  Defines Koin modules (e.g., `networkModule`, `repositoryModule`, `viewModelModule`, `adapterModule`) for dependency injection configuration.


## Setup and Installation

### Prerequisites

- Android Studio (latest stable version recommended)
- Kotlin support and Gradle build system

### Installation Steps

1. **Clone the Repository**

   ```bash
   git clone https://github.com/ShingHanHuang/MVI_DEMO.git
   cd mvi_demo
