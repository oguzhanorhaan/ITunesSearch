package com.oguzhanorhan.itunessearch.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseVM :
    ViewModel(),
    CoroutineScope {
    private val job = Job()

    override val coroutineContext: CoroutineContext get() = job + Dispatchers.Main

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
