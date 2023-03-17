//
//  ChatScreen.swift
//  iosApp
//
//  Created by Borealin on 2023/3/16.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared
import SwiftUI
struct ChatScreen: View {
    @StateObject var viewModel = ChatViewModel()
    @State private var showToast: Bool = false
    var body: some View {
        ChatScreenInner(
            chatMessages: $viewModel.chatMessages,
            typingMessage: $viewModel.typingMessage
        ) {
            viewModel.getCompletion()
        }.onAppear {
            viewModel.observeFlow()
        }
        .toast(message: $viewModel.error)
    }
}

struct ChatScreenInner: View {
    @Binding var chatMessages: [ChatMessageViewData]
    @Binding var typingMessage: String
    let getCompletion: () -> Void

    var body: some View {
        VStack {
            ScrollView {
                LazyVStack {
                    ForEach(chatMessages.filter { $0.role.isShow }, id: \.id) { message in
                        ChatMessageBubble(message: message)
                    }
                }
            }
            .padding(.top)

            HStack {
                TextField("Type a message", text: $typingMessage)
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                    .padding(.horizontal)

                Button(action: {
                    getCompletion()
                }) {
                    Image(systemName: "paperplane.fill")
                        .font(.system(size: 24))
                        .padding(.trailing)
                }
            }
            .padding(.bottom)
        }
        .navigationBarTitle("", displayMode: .automatic)
    }
}

#if DEBUG
struct ChatScreenInnerPreview: View {
    @State var chatMessages: [ChatMessageViewData]
    @State var typingMessage: String = ""
    var body: some View {
        ChatScreenInner(
            chatMessages: $chatMessages,
            typingMessage: $typingMessage
        ) {}
    }
}

struct ChatScreen_Previews: PreviewProvider {
    @State var typingMessage: String
    static var previews: some View {
        ChatScreenInnerPreview(chatMessages: [
            ChatMessageViewData(role: .user, isLoading: false, content: "Hello", id: UUID().uuidString),
            ChatMessageViewData(role: .assistant, isLoading: false, content: "Hello too", id: UUID().uuidString),
        ])
    }
}
#endif
