//
//  TapToDismissFocus.swift
//  iosApp
//
//  Created by Borealin on 2023/3/20.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct TapToDismissFocus: ViewModifier {
    @FocusState var focusState: Bool

    func body(content: Content) -> some View {
        content
            .onTapGesture {
                focusState = false
            }
    }
}
