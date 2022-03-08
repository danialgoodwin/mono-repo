# Android ViewModel Cheat Sheet



## Setup

In build.gradle:

    implementation 'androidx.core:core-ktx:1.6.0' // https://developer.android.com/jetpack/androidx/releases/core

Implementation:

    import androidx.core.text.set
    import androidx.core.text.toSpannable
    
    "My NEW string".toSpannable().apply() {
        var index = this.indexOf("NEW")
        if (index == -1) return@apply
        this[index, index + 3] = UnderlineSpan()
    }



## Basics
- For multiple Fragments to share the same ViewModel, use the Activity context when instantiating the ViewModel



## Resources
- https://developer.android.com/reference/kotlin/androidx/core/text/package-summary
- https://developer.android.com/reference/android/text/style/BackgroundColorSpan
- https://developer.android.com/reference/android/text/style/ClickableSpan
- https://developer.android.com/reference/android/text/style/DrawableMarginSpan: A span which adds a drawable and a padding to the paragraph it's attached to
- https://developer.android.com/reference/android/text/style/EasyEditSpan: Allows the user to delete a chuck of text in one click
- https://developer.android.com/reference/android/text/style/QuoteSpan
- https://developer.android.com/reference/android/text/style/RelativeSizeSpan
- https://developer.android.com/reference/android/text/style/StrikethroughSpan
- https://developer.android.com/reference/android/text/style/SubscriptSpan
- https://developer.android.com/reference/android/text/style/UnderlineSpan
- https://developer.android.com/reference/android/text/style/URLSpan
