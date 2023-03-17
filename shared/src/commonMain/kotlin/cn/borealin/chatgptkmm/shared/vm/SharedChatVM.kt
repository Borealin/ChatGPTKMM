package cn.borealin.chatgptkmm.shared.vm

import cn.borealin.chatgptkmm.shared.entity.ChatMessageViewData
import cn.borealin.chatgptkmm.shared.entity.ChatMessageViewData.Companion.toChatMessageViewData
import cn.borealin.chatgptkmm.shared.entity.ChatRole
import cn.borealin.chatgptkmm.shared.entity.SessionConfiguration
import cn.borealin.chatgptkmm.shared.repo.IChatRepository
import com.rickclephas.kmp.nativecoroutines.NativeCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class SharedChatVM constructor(
    private val chatRepository: IChatRepository,
) : KoinComponent {

    @NativeCoroutineScope
    private val viewModelScope = CoroutineScope(Dispatchers.Default)

    private var completionJob = MutableStateFlow<Job?>(null)

    private val _sessionConfigure = MutableStateFlow(SessionConfiguration())
    val sessionConfigure get() = _sessionConfigure.asStateFlow()

    private val _chatMessages = MutableStateFlow(listOf<ChatMessageViewData>())
    val chatMessages get() = _chatMessages.asStateFlow()

    private val _typingMessage = MutableStateFlow("")
    val typingMessage get() = _typingMessage.asStateFlow()

    val error = MutableStateFlow<String?>(null)

    fun updateTypingMessage(message: String) {
        _typingMessage.value = message
    }

    fun updateError(message: String?) {
        error.value = message
    }

    fun getCompletion() {
        if (completionJob.value != null) {
            return
        }
        val typingMessage = _typingMessage.value
        if (typingMessage.isBlank()) {
            error.value = "Please input something"
            return
        }
        _typingMessage.value = ""
        val originalMessages = _chatMessages.value
        val requestMessage = originalMessages + ChatMessageViewData(
            role = ChatRole.USER,
            isLoading = false,
            content = typingMessage,
        )
        _chatMessages.value = requestMessage + ChatMessageViewData(
            role = ChatRole.ASSISTANT,
            isLoading = true,
        )
        val job = viewModelScope.launch {
            chatRepository
                .getCompletion(
                    requestMessage.map { it.toChatMessage() }, _sessionConfigure.value
                )
                .collect { result ->
                    result.onSuccess { messages ->
                        _chatMessages.value = messages.map { it.toChatMessageViewData() }
                    }.onFailure {
                        error.value = it.message
                        _chatMessages.value = originalMessages
                        _typingMessage.value = typingMessage
                    }
                }
        }
        completionJob.value = job
        job.invokeOnCompletion {
            completionJob.value = null
        }
    }
}

