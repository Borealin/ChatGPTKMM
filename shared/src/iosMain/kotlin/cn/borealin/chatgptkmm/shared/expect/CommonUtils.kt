package cn.borealin.chatgptkmm.shared.expect

import platform.Foundation.NSUUID

actual fun uuid(): String {
    return NSUUID().UUIDString()
}