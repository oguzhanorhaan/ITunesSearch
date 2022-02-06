package com.oguzhanorhan.itunessearch.data.model

data class Resource<out T>(val status: ITunesApiStatus, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(ITunesApiStatus.DONE, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(ITunesApiStatus.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(ITunesApiStatus.LOADING, data, null)
        }
    }
}