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
            chatMessages: viewModel.chatMessages,
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
    var chatMessages: [ChatMessageViewData]
    @Binding var typingMessage: String
    @FocusState private var isTextFieldFocused: Bool
    let getCompletion: () -> Void

    var body: some View {
        VStack {
            ScrollViewReader { scrollView in
                ScrollView {
                    LazyVStack {
                        ForEach(chatMessages.filter { $0.role.isShow }, id: \.self) { message in
                            ChatMessageBubble(message: message)
                        }
                    }
                }.onChange(of: chatMessages) { _ in
                    let count = chatMessages.count
                    guard count > 0 else {
                        return
                    }
                    isTextFieldFocused = false
                    scrollView.scrollTo(chatMessages[count - 1], anchor: .bottom)
                }
            }
            .padding(.top)

            HStack {
                TextField("Type a message", text: $typingMessage)
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                    .padding(.horizontal)
                    .focused($isTextFieldFocused)

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
        .modifier(TapToDismissFocus(focusState: _isTextFieldFocused))
    }
}

#if DEBUG
struct ChatScreenInnerPreview: View {
    @State var chatMessages: [ChatMessageViewData]
    @State var typingMessage: String = ""
    var body: some View {
        ChatScreenInner(
            chatMessages: chatMessages,
            typingMessage: $typingMessage
        ) {}
    }
}

struct ChatScreen_Previews: PreviewProvider {
    @State var typingMessage: String
    static var previews: some View {
        ChatScreenInnerPreview(chatMessages: [
            ChatMessageViewData(role: .user, isLoading: false, content: "Hello"),
            ChatMessageViewData(role: .assistant, isLoading: false, content: "Hello too"),
        ])
    }
}
#endif
