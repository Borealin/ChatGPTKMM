//
//  ChatMessageBubble.swift
//  iosApp
//
//  Created by Borealin on 2023/3/16.
//  Copyright © 2023 orgName. All rights reserved.
//

import MarkdownUI
import shared
import Splash
import SwiftUI

internal extension ChatMessageViewData {
    convenience init(role: ChatRole, isLoading: Bool, content: String) {
        self.init(role: role, isLoading: isLoading, content: content, id: UUID().uuidString)
    }
}

struct ChatMessageBubble: View {
    let message: ChatMessageViewData
    @State private var rotation = 0.0
    @Environment(\.colorScheme) private var colorScheme
    var body: some View {
        HStack {
            if self.message.role.isUser {
                Spacer()
            }

            HStack {
                if self.message.isLoading {
                    Circle()
                        .trim(from: 0.5, to: 1.0)
                        .stroke(Color.white, lineWidth: 4)
                        .rotationEffect(Angle(degrees: self.rotation))
                        .onAppear {
                            withAnimation(Animation.linear(duration: 1.0).repeatForever(autoreverses: false)) {
                                self.rotation = 360
                            }
                        }
                        .frame(width: 40, height: 40)
                        .padding(12)

                } else {
                    Markdown(MarkdownContent(self.message.content))
                        .markdownTextStyle {
                            ForegroundColor(.white)
                        }
//                        .markdownCodeSyntaxHighlighter(.splash(theme: self.theme))
                }
            }.padding(12)
                .background(self.message.role.isUser ? Color.blue : Color.gray)
                .cornerRadius(12)
            if !self.message.role.isUser {
                Spacer()
            }
        }
        .padding(.horizontal, 16)
        .padding(.vertical, 4)
    }
//
//    private var theme: Splash.Theme {
//        switch self.colorScheme {
//        case .dark:
//            return .wwdc18(withFont: .init(size: 16))
//        default:
//            return .sunset(withFont: .init(size: 16))
//        }
//    }
}

#if DEBUG
struct ChatMessageBubbleUser_Previews: PreviewProvider {
    static var previews: some View {
        ChatMessageBubble(message: ChatMessageViewData(role: .user, isLoading: false, content: "Hello"))
    }
}

struct ChatMessageBubbleAssistant_Previews: PreviewProvider {
    static var previews: some View {
        ChatMessageBubble(message: ChatMessageViewData(role: .assistant, isLoading: false, content: "Hello"))
    }
}

struct ChatMessageBubbleLoading_Previews: PreviewProvider {
    static var previews: some View {
        ChatMessageBubble(message: ChatMessageViewData(role: .user, isLoading: true, content: "Hello"))
    }
}
#endif
