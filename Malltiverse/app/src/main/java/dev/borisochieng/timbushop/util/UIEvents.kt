package dev.borisochieng.timbushop.util

sealed class UIEvents {
    data class SnackBarEvent(val message: String): UIEvents()
}