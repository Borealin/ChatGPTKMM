package cn.borealin.chatgptkmm.shared.di

import cn.borealin.chatgptkmm.shared.expect.platformModule
import cn.borealin.chatgptkmm.shared.vm.SharedChatVM
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(enableNetworkLogs: Boolean = true, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(platformModule(), commonModule(enableNetworkLogs = enableNetworkLogs))
    }

fun KoinApplication.Companion.start(): KoinApplication = initKoin { }

val Koin.chatVM: SharedChatVM
    get() = get()