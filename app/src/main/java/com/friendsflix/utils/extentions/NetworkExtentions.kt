package com.friendsflix.utils.extentions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit

fun <T> CoroutineScope.launchSuspendFun(
    block: suspend CoroutineScope.() -> T,
    onSuccess: ((T) -> Unit)? = null,
    onLoading: ((Boolean) -> Unit)? = null,
    onError: ((Throwable) -> Unit)? = null
) {
    launch {
        onLoading?.invoke(true)
        try {
            val result = block()
            onLoading?.invoke(false)
            onSuccess?.invoke(result)
        } catch (t: Throwable) {
            onLoading?.invoke(false)
            onError?.invoke(t)
        }
    }
}

inline fun <reified T> service(retrofit: Retrofit.Builder): Lazy<T> = lazy {
    retrofit.build().create(T::class.java)
}