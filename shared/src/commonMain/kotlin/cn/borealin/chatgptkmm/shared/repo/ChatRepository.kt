package cn.borealin.chatgptkmm.shared.repo

import cn.borealin.chatgptkmm.shared.entity.ChatMessage
import cn.borealin.chatgptkmm.shared.entity.SessionConfiguration
import cn.borealin.chatgptkmm.shared.net.ChatApi
import cn.borealin.chatgptkmm.shared.net.util.safeApiCall

class ChatRepository(
    private val api: ChatApi
) : IChatRepository {
    override suspend fun getCompletion(
        messages: List<ChatMessage>,
        configuration: SessionConfiguration
    ) = safeApiCall {
        val response = api.getCompletion(messages, configuration)
        return@safeApiCall messages + response.choices[0].message
    }
}