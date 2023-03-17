package cn.borealin.chatgptkmm.shared.net

import cn.borealin.chatgptkmm.shared.entity.ChatMessage
import cn.borealin.chatgptkmm.shared.entity.SessionConfiguration
import cn.borealin.chatgptkmm.shared.net.model.ChatCompletionRequest
import cn.borealin.chatgptkmm.shared.net.model.ChatCompletionResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ChatApi constructor(private val httpClient: HttpClient) {
    suspend fun getCompletion(
        messages: List<ChatMessage>,
        sessionConfiguration: SessionConfiguration
    ): ChatCompletionResponse = httpClient.post("chat/completions") {
        contentType(ContentType.Application.Json)
        setBody(
            ChatCompletionRequest(
                sessionConfiguration.model,
                messages,
                sessionConfiguration.temperature,
                sessionConfiguration.topP,
                sessionConfiguration.n,
                false,
                sessionConfiguration.stop,
                sessionConfiguration.maxTokens,
                sessionConfiguration.presencePenalty,
                sessionConfiguration.frequencyPenalty,
                sessionConfiguration.logitBias,
                sessionConfiguration.user,
            )
        )
    }.body()
}

