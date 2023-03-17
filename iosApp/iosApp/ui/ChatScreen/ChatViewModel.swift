//
//  ChatViewModel.swift
//  iosApp
//
//  Created by Borealin on 2023/3/17.
//  Copyright © 2023 orgName. All rights reserved.
//

import Combine
import Foundation
import KMPNativeCoroutinesAsync
import KMPNativeCoroutinesCombine
import shared
import SwiftUI

@MainActor
class ChatViewModel: ObservableObject {
    @LazyKoin private var sharedVM: SharedChatVM
    private var cancellables: [AnyCancellable] = []

    @Published var chatMessages: [ChatMessageViewData] = []
    @Published private var _typingMessage: String = ""
    var typingMessage: String {
        get {
            _typingMessage
        }
        set {
            sharedVM.updateTypingMessage(message: newValue)
        }
    }

    @Published private var _error: String? = nil
    var error: String? {
        get {
            _error
        }
        set {
            sharedVM.updateError(message: newValue)
        }
    }

    func observeFlow() {
        createPublisher(for: sharedVM.chatMessagesNative)
            .receive(on: DispatchQueue.main)
            .sink(receiveCompletion: { _ in }) {
                self.chatMessages = $0
            }
            .store(in: &cancellables)
        createPublisher(for: sharedVM.typingMessageNative)
            .receive(on: DispatchQueue.main)
            .sink(receiveCompletion: { _ in }) {
                self._typingMessage = $0
            }
            .store(in: &cancellables)
        createPublisher(for: sharedVM.errorNative)
            .receive(on: DispatchQueue.main)
            .sink(receiveCompletion: { _ in }) {
                self._error = $0
            }
            .store(in: &cancellables)
    }

    func getCompletion() {
        sharedVM.getCompletion()
    }
}
