package cn.borealin.chatgptkmm.android

import android.app.Application
import cn.borealin.chatgptkmm.shared.di.initKoin
import cn.borealin.chatgptkmm.shared.logging.NapierInit
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class ChatApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val isDebug = BuildConfig.DEBUG
        initKoin(isDebug) {
            androidLogger(level = if (isDebug) Level.ERROR else Level.NONE)
            androidContext(androidContext = this@ChatApplication)
        }
        if (isDebug) NapierInit().init()
    }
}