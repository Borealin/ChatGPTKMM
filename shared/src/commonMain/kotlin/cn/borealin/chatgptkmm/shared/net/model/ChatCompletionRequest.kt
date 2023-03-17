package cn.borealin.chatgptkmm.shared.net.model

import cn.borealin.chatgptkmm.shared.entity.ChatMessage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionRequest(
    @SerialName("model")
    val model: ChatModel,
    @SerialName("messages")
    val messages: List<ChatMessage>,
    @SerialName("temperature")
    val temperature: Double,
    @SerialName("top_p")
    val topP: Double,
    @SerialName("n")
    val n: Int,
    @SerialName("stream")
    val stream: Boolean,
    @SerialName("stop")
    val stop: List<String>?,
    @SerialName("max_tokens")
    val maxTokens: Int?,
    @SerialName("presence_penalty")
    val presencePenalty: Double,
    @SerialName("frequency_penalty")
    val frequencyPenalty: Double,
    @SerialName("logit_bias")
    val logitBias: Map<String, Double>?,
    @SerialName("user")
    val user: String?,
)