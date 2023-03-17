package cn.borealin.chatgptkmm.shared.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ChatRole {
    @SerialName("system")
    SYSTEM,

    @SerialName("user")
    USER,

    @SerialName("assistant")
    ASSISTANT;

    val isShow: Boolean
        get() = this != SYSTEM
    val isUser: Boolean
        get() = this == USER
}