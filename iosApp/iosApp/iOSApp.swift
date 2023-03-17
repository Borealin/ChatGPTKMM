import shared
import SwiftUI

@main
struct iOSApp: App {
    init() {
        KoinApplication.start()
        #if DEBUG
        NapierInit().doInit()
        #endif
    }

    var body: some Scene {
        WindowGroup {
            ChatScreen()
        }
    }
}
