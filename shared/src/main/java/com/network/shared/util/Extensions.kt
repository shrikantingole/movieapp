package com.network.shared.util

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Parcel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestListener

/**
 * Implementation of lazy that is not thread safe. Useful when you know what thread you will be
 * executing on and are not worried about synchronization.
 */
fun <T> lazyFast(operation: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE) {
    operation()
}

/** Convenience for callbacks/listeners whose return value indicates an event was consumed. */
inline fun consume(f: () -> Unit): Boolean {
    f()
    return true
}

/**
 * Allows calls like
 *
 * `viewGroup.inflate(R.layout.foo)`
 */
fun ViewGroup.inflate(@LayoutRes layout: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layout, this, attachToRoot)
}

/**
 * Allows calls like
 *
 * `supportFragmentManager.inTransaction { add(...) }`
 */
inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}


/**
 * For Actvities, allows declarations like
 * ```
 * val myViewModel = viewModelProvider(myViewModelFactory)
 * ```
 */
inline fun <reified VM : ViewModel> FragmentActivity.viewModelProvider(
    provider: ViewModelProvider.Factory
) =
    ViewModelProvider(this, provider).get(VM::class.java)

/**
 * For Fragments, allows declarations like
 * ```
 * val myViewModel = viewModelProvider(myViewModelFactory)
 * ```
 */
inline fun <reified VM : ViewModel> Fragment.viewModelProvider(
    provider: ViewModelProvider.Factory
) =
    ViewModelProvider(this, provider).get(VM::class.java)

/**
 * Like [Fragment.viewModelProvider] for Fragments that want a [ViewModel] scoped to the Activity.
 */
inline fun <reified VM : ViewModel> Fragment.activityViewModelProvider(
    provider: ViewModelProvider.Factory
) =
    ViewModelProvider(requireActivity(), provider).get(VM::class.java)

/**
 * Like [Fragment.viewModelProvider] for Fragments that want a [ViewModel] scoped to the parent
 * Fragment.
 */
inline fun <reified VM : ViewModel> Fragment.parentViewModelProvider(
    provider: ViewModelProvider.Factory
) =
    ViewModelProvider(parentFragment!!, provider).get(VM::class.java)

// endregion
// region Parcelables, Bundles

/** Write an enum value to a Parcel */
fun <T : Enum<T>> Parcel.writeEnum(value: T) = writeString(value.name)

/** Read an enum value from a Parcel */
inline fun <reified T : Enum<T>> Parcel.readEnum(): T = enumValueOf(readString() ?: "")

/** Write an enum value to a Bundle */
fun <T : Enum<T>> Bundle.putEnum(key: String, value: T) = putString(key, value.name)

/** Read an enum value from a Bundle */
inline fun <reified T : Enum<T>> Bundle.getEnum(key: String): T = enumValueOf(getString(key) ?: "")

/** Write a boolean to a Parcel (copied from Parcel, where this is @hidden). */
fun Parcel.writeBoolean(value: Boolean) = writeInt(if (value) 1 else 0)

/** Read a boolean from a Parcel (copied from Parcel, where this is @hidden). */
fun Parcel.readBoolean() = readInt() != 0

// endregion
// region LiveData

/** Uses `Transformations.map` on a LiveData */
fun <X, Y> LiveData<X>.map(body: (X) -> Y): LiveData<Y> {
    return Transformations.map(this, body)
}

/** Uses `Transformations.switchMap` on a LiveData */
fun <X, Y> LiveData<X>.switchMap(body: (X) -> LiveData<Y>): LiveData<Y> {
    return Transformations.switchMap(this, body)
}

fun <T> MutableLiveData<T>.setValueIfNew(newValue: T) {
    if (this.value != newValue) value = newValue
}

fun <T> MutableLiveData<T>.postValueIfNew(newValue: T) {
    if (this.value != newValue) postValue(newValue)
}
// endregion

/**
 * Helper to force a when statement to assert all options are matched in a when statement.
 *
 * By default, Kotlin doesn't care if all branches are handled in a when statement. However, if you
 * use the when statement as an expression (with a value) it will force all cases to be handled.
 *
 * This helper is to make a lightweight way to say you meant to match all of them.
 *
 * Usage:
 *
 * ```
 * when(sealedObject) {
 *     is OneType -> //
 *     is AnotherType -> //
 * }.checkAllMatched
 */
val <T> T.checkAllMatched: T
    get() = this

// region UI utils

/**
 * Retrieves a color from the theme by attributes. If the attribute is not defined, a fall back
 * color will be returned.
 */


fun ImageView.loadImage(data: Any) {
    loadImage(data, null)
}

fun ImageView.loadImage(data: Any, listener: RequestListener<Drawable>?) {
    Glide.with(context)
        .load(data)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .timeout(60000)
        .listener(listener)
        .into(this)
}


fun ImageView.loadImage(imageUrl: String?, placeHolder: Int) {
    val image: Any = if (imageUrl.isNullOrEmpty()) placeHolder else imageUrl
    loadImage(image)
}

