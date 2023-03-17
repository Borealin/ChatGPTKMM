package cn.borealin.chatgptkmm.shared.entity

import cn.borealin.chatgptkmm.shared.net.model.ChatModel

data class SessionConfiguration(
    val model: ChatModel = ChatModel.GPT_3_5_TURBO,
    val temperature: Double = 1.0,
    val topP: Double = 1.0,
    val n: Int = 1,
    val stop: List<String>? = null,
    val maxTokens: Int? = null,
    val presencePenalty: Double = 0.0,
    val frequencyPenalty: Double = 0.0,
    val logitBias: Map<String, Double>? = null,
    val user: String? = null,
)