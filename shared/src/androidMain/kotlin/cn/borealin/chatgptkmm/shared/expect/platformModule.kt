package cn.borealin.chatgptkmm.shared.expect

import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { DatabaseDriverFactory(get()) }
    single { MultiplatformSettingsWrapper(get()).createSettings() }
}