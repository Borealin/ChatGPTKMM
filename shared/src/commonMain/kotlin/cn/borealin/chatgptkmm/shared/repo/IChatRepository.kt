package cn.borealin.chatgptkmm.shared.repo

import cn.borealin.chatgptkmm.shared.entity.ChatMessage
import cn.borealin.chatgptkmm.shared.entity.SessionConfiguration
import kotlinx.coroutines.flow.Flow

interface IChatRepository {
    suspend fun getCompletion(
        messages: List<ChatMessage>,
        configuration: SessionConfiguration
    ): Flow<Result<List<ChatMessage>>>
}