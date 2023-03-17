//
//  ChatMessageBubble.swift
//  iosApp
//
//  Created by Borealin on 2023/3/16.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared
import SwiftUI

struct ChatMessageBubble: View {
    let message: ChatMessageViewData
    @State private var rotation = 0.0
    var body: some View {
        HStack {
            if message.role.isUser {
                Spacer()
            }

            HStack {
                if message.isLoading {
                    Circle()
                        .trim(from: 0.5, to: 1.0)
                        .stroke(Color.white, lineWidth: 4)
                        .rotationEffect(Angle(degrees: rotation))
                        .onAppear {
                            withAnimation(Animation.linear(duration: 1.0).repeatForever(autoreverses: false)) {
                                rotation = 360
                            }
                        }
                        .frame(width: 40, height: 40)
                        .padding(12)

                } else {
                    Text(message.content)
                }
            }.padding(12)
                .background(message.role.isUser ? Color.blue : Color.gray)
                .foregroundColor(.white)
                .cornerRadius(12)
            if !message.role.isUser {
                Spacer()
            }
        }
        .padding(.horizontal, 16)
        .padding(.vertical, 4)
    }
}

#if DEBUG
struct ChatMessageBubbleUser_Previews: PreviewProvider {
    static var previews: some View {
        ChatMessageBubble(message: ChatMessageViewData(role: .user, isLoading: false, content: "Hello", id: UUID().uuidString))
    }
}

struct ChatMessageBubbleAssistant_Previews: PreviewProvider {
    static var previews: some View {
        ChatMessageBubble(message: ChatMessageViewData(role: .assistant, isLoading: false, content: "Hello", id: UUID().uuidString))
    }
}

struct ChatMessageBubbleLoading_Previews: PreviewProvider {
    static var previews: some View {
        ChatMessageBubble(message: ChatMessageViewData(role: .user, isLoading: true, content: "Hello", id: UUID().uuidString))
    }
}
#endif
