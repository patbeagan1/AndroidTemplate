# AndroidSample

This repository is a learning area for the most recent Kotlin, Jetpack, Architecture and Compose updates.

Some of the technologies included are:
- MVVM
- Clean Architecture
- LiveData
- ViewModel
- Coroutines
- Compose
- Databinding
- Hilt

---

The file structure is set up like this

```
.
├── App.kt
├── MainActivity.kt
├── data
│    ├── Result.kt
│    ├── network
│    │   └── <feature>api
│    │       ├── <Feature>Service.kt
│    │       └── response
│    │           └── <Each>Response.kt
│    └── repository
│        └── RepositoryImpl.kt
├── di
│    ├── Module.kt
│    └── qualifiers
│        └── <Each>.kt
├── domain
│    ├── <Feature>UseCase.kt
│    └── entities
│        └── Entity<Each>.kt
├── ui
│    ├── BindingAdapters.kt
│    └── <feature>
│        ├── <Feature>Fragment.kt
│        ├── <Feature>RecyclerviewAdapter.kt
│        ├── <Feature>ViewModel.kt
│        └── adapter
│            └── ViewHolder.kt
└── util
    └── Extensions.kt
```

## References
- https://medium.com/androiddevelopers/coroutines-on-android-part-iii-real-work-2ba8a2ec2f45
- https://github.com/android/architecture-components-samples/tree/main/LiveDataSample
- https://developer.android.com/kotlin/coroutines
- https://developer.android.com/topic/libraries/architecture/coroutines#livedata
- https://kotlinlang.org/docs/flow.html
- https://proandroiddev.com/why-you-need-use-cases-interactors-142e8a6fe576
- https://medium.com/captech-corner/standardizing-android-dependency-injection-with-hilt-bd8b4c93fd27
- ✨ https://medium.com/@asheshb/sample-app-android-app-architecture-by-example-part-5-5-a57ec04a93d9
- https://developer.android.com/training/dependency-injection/hilt-android
- https://adambennett.dev/2019/07/mvi-the-good-the-bad-and-the-ugly/
- https://docs.thecatapi.com
- https://medium.com/androiddevelopers/coroutines-on-android-part-iii-real-work-2ba8a2ec2f45
- https://github.com/vinaygaba/Learn-Jetpack-Compose-By-Example/blob/master/app/src/main/java/com/example/jetpackcompose/state/livedata/LiveDataActivity.kt
- https://developer.android.com/codelabs/jetpack-compose-basics#4
- https://medium.com/androiddevelopers/migrating-from-livedata-to-kotlins-flow-379292f419fb
- https://developer.android.com/codelabs/advanced-kotlin-coroutines#1
- https://proandroiddev.com/mediatorlivedata-to-the-rescue-5d27645b9bc3
- https://www.section.io/engineering-education/getting-started-with-jetpack-compose-in-android/
- https://github.com/android/compose-samples/blob/main/Jetcaster/buildSrc/src/main/java/com/example/jetcaster/buildsrc/dependencies.kt
- https://stackoverflow.com/questions/58040778/android-difference-between-databinding-and-viewbinding
- ✨ https://github.com/android/sunflower
- https://ricardocosteira.com/going-with-the-flow-rxjava-to-coroutines-part-1
- https://medium.com/@ibaljeet/retrofit-api-calls-using-coroutine-and-suspend-functions-for-beginners-e30263f63b9a
- https://bytestechnolab.medium.com/an-overview-of-android-mvvm-view-model-with-live-data-b20f47f8dfca
- https://medium.com/tompee/kotlin-annotation-processor-and-code-generation-58bd7d0d333b

### Troubleshooting:
https://medium.com/@martin.crabtree/android-sdk-manually-setting-a-dns-for-android-emulator-6b14028e9667