package dev.borisochieng.timbushop.data

sealed class NetworkResponse<T>(val payLoad: T? = null, val message: String? = null) {
    class Success<T>(payLoad: T?) : NetworkResponse<T>(payLoad = payLoad)
    class Error<T>(message: String, payLoad: T? = null) : NetworkResponse<T>(payLoad, message)
}