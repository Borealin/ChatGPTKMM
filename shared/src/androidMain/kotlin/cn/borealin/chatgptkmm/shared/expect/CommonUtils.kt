package cn.borealin.chatgptkmm.shared.expect

import java.util.UUID

actual fun uuid(): String {
    return UUID.randomUUID().toString()
}