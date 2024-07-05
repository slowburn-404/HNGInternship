package dev.borisochieng.timbushop.data

sealed class Resource<T>(val payLoad: T? = null, val message: String? = null) {
    class Success<T>(payLoad: T?) : Resource<T>(payLoad = payLoad)
    class Error<T>(message: String, payLoad: T? = null) : Resource<T>(payLoad, message)
}