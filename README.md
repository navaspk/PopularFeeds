HLD: This has been implemented based on Clean architecture with MVVM.
Entity: Having enterprise rules, which contains with some data classes
Use cases: Taken care of flow of data from and to entities
Controller/Presenter: Taking up the data and converting to convenient wa for UI
UI: Take care of showing the content to user
Domain consists of business logic which contains server data model repository and use cases

LLD: 
Platform : Android
Language: Kotlin 
Library used:
1) Dagger2
2) RxJava
3) Glide
4) Jetifier
5) AndroidX
6) NavigatorController
7) Retrofit
8) OkHttp

Retrofit is a type safe http client used to getting data from server, it calling to server by using
java interface with many request methods and request params. Okhttp used for network call. 
RxJava is another library added in this to get asynchronous call. This is working based on observer
and observable concept. Now a days RxJava is more important as this is having good feature like able
to perform network call, perform synchronous and asynchronous call, performing background listening.

Application Architecture

Using MVVM architecture with clean architecture


Just clone the repository and build. Make sure your system has active internet connection to download
all the dependencies and need to add your own app key in app's build.gradle.
On blow "xxx" need to be replace with your own key, so that with this approach we can make sure security
in app keys as we added in build.gradle

buildConfigField "String", "API_KEY", "\"xxx\""



