package cn.borealin.chatgptkmm.shared.net.model

import cn.borealin.chatgptkmm.shared.entity.ChatMessage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionChoice(
    @SerialName("index")
    val index: Int,
    @SerialName("message")
    val message: ChatMessage,
    @SerialName("finish_reason")
    val finishReason: String,
)