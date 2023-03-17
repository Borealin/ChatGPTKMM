package cn.borealin.chatgptkmm.android.ui.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.borealin.chatgptkmm.android.ui.chat.components.ChatMessageBubble
import cn.borealin.chatgptkmm.android.ui.components.ShowToast
import cn.borealin.chatgptkmm.shared.entity.ChatMessageViewData
import cn.borealin.chatgptkmm.shared.entity.ChatRole
import cn.borealin.chatgptkmm.shared.vm.SharedChatVM
import org.koin.androidx.compose.get

@Composable
fun ChatScreen(vm: SharedChatVM = get()) {
    val messages = vm.chatMessages.collectAsState().value
    val typingMessage = vm.typingMessage.collectAsState().value
    ShowToast(vm.error)
    ChatScreenInner(
        messages,
        typingMessage,
        vm::updateTypingMessage
    ) {
        vm.getCompletion()
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun ChatScreenInner(
    messages: List<ChatMessageViewData>,
    typingMessage: String,
    onValueChange: (String) -> Unit,
    onSendMessage: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val listState = rememberLazyListState()
    LaunchedEffect(messages) {
        if (messages.isEmpty()) return@LaunchedEffect
        listState.animateScrollToItem(messages.size - 1)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = listState,
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(messages.filter { it.role.isShow }) { message ->
                ChatMessageBubble(message)
            }
        }

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextField(
                value = typingMessage,
                onValueChange = onValueChange,
                modifier = Modifier.weight(1f),
                label = { Text("Type a message") }
            )

            IconButton(onClick = {
                keyboardController?.hide()
                focusManager.clearFocus()
                onSendMessage()
            }) {
                Icon(Icons.Default.Send, contentDescription = "Send")
            }
        }
    }
}

@Preview
@Composable
fun ChatScreenPreview() {
    ChatScreenInner(
        listOf(
            ChatMessageViewData(
                role = ChatRole.USER,
                content = "Hello, World!"
            ),
            ChatMessageViewData(
                role = ChatRole.ASSISTANT,
                content = "Hello, World!"
            )
        ), "Nice to meet you", {}, {})
}
