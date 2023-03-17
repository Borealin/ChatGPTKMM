package cn.borealin.chatgptkmm.shared.net.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ChatModel {
    @SerialName("gpt-3.5-turbo")
    GPT_3_5_TURBO,

    @SerialName("gpt-4")
    GPT_4,
}