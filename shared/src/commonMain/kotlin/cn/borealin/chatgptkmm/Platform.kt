package cn.borealin.chatgptkmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform