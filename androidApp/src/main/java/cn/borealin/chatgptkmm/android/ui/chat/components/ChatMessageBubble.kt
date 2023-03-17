package cn.borealin.chatgptkmm.android.ui.chat.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.borealin.chatgptkmm.shared.entity.ChatMessageViewData
import cn.borealin.chatgptkmm.shared.entity.ChatRole
import dev.jeziellago.compose.markdowntext.MarkdownText

@Composable
fun ChatMessageBubble(message: ChatMessageViewData) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .fillMaxWidth(),
        horizontalArrangement = if (message.role.isUser) Arrangement.End else Arrangement.Start
    ) {
        if (!message.role.isUser) {
            Spacer(modifier = Modifier.width(16.dp))
        }

        Surface(
            color = if (message.role.isUser) MaterialTheme.colors.primary else Color.Gray,
            shape = RoundedCornerShape(8.dp)
        ) {
            if (message.isLoading) {
                Box(
                    modifier = Modifier.padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = Color.White,
                        strokeWidth = 4.dp
                    )
                }
            } else {
                MarkdownText(
                    markdown = message.content,
                    modifier = Modifier.padding(8.dp),
                    color = Color.White
                )
            }
        }

        if (message.role.isUser) {
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}

@Preview
@Composable
fun ChatBubblePreview() {
    ChatMessageBubble(
        ChatMessageViewData(
            role = ChatRole.USER,
            content = "Hello, World!"
        )
    )
}

@Preview
@Composable
fun ChatBubbleLoadingPreview() {
    ChatMessageBubble(
        ChatMessageViewData(
            isLoading =  true,
            role = ChatRole.ASSISTANT,
            content = ""
        )
    )
}