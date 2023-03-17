package cn.borealin.chatgptkmm.shared.entity

import cn.borealin.chatgptkmm.shared.expect.uuid
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatMessage(
    @SerialName("role")
    val role: ChatRole,
    @SerialName("content")
    val content: String,
)

data class ChatMessageViewData(
    val role: ChatRole = ChatRole.ASSISTANT,
    val isLoading: Boolean = false,
    val content: String = "",
    val id: String = uuid(),
) {
    fun toChatMessage() = ChatMessage(role, content)

    companion object {
        fun ChatMessage.toChatMessageViewData() = ChatMessageViewData(
            role = role,
            content = content,
        )
    }
}