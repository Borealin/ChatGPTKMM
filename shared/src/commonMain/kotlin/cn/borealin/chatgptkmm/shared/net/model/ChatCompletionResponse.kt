package cn.borealin.chatgptkmm.shared.net.model

import cn.borealin.chatgptkmm.shared.entity.IUsageResponse
import cn.borealin.chatgptkmm.shared.entity.Usage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionResponse(
    @SerialName("id")
    val id: String,
    @SerialName("object")
    val `object`: String,
    @SerialName("created")
    val created: Long,
    @SerialName("model")
    val model: String,
    @SerialName("choices")
    val choices: List<ChatCompletionChoice>,
    @SerialName("usage")
    override val usage: Usage,
) : IUsageResponse