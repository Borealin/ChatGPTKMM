//
//  ToastExtension.swift
//  iosApp
//
//  Created by Borealin on 2023/3/17.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
struct ToastView: View {
    let message: String

    var body: some View {
        Text(message)
            .foregroundColor(.white)
            .padding(.all, 16)
            .background(Color.black.opacity(0.8))
            .cornerRadius(10)
    }
}

struct ToastModifier: ViewModifier {
    @Binding var toastMessage: String?
    let duration: TimeInterval

    func body(content: Content) -> some View {
        ZStack {
            content

            if let message = toastMessage {
                ToastView(message: message)
                    .padding(.bottom, 50)
                    .transition(.move(edge: .bottom).combined(with: .opacity))
                    .onAppear {
                        DispatchQueue.main.asyncAfter(deadline: .now() + duration) {
                            withAnimation {
                                toastMessage = nil
                            }
                        }
                    }
            }
        }
        .animation(.easeInOut, value: toastMessage)
    }
}

extension View {
    func toast(message: Binding<String?>, duration: TimeInterval = 2) -> some View {
        self.modifier(ToastModifier(toastMessage: message, duration: duration))
    }
}
