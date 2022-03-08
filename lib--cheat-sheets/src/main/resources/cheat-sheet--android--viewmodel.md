# Android ViewModel Cheat Sheet



## Setup

In build.gradle:

    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.3.1' // https://developer.android.com/jetpack/androidx/releases/lifecycle
    implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.3.1'
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1'

Instantiate:

    // In Activity or Fragment
    val model = ViewModelProviders.of(this).get(MyViewModel::class.java)
    
    // In View
    val model = ViewModelProviders.of(context as FragmentActivity).get(MyViewModel::class.java)

Using coroutine:

    viewModelScope.launch(Dispatchers.IO) {
        ...
        myLiveData.postValue(...)
    }



## Basics
- For multiple Fragments to share the same ViewModel, use the Activity context when instantiating the ViewModel



## Resources
- TODO
