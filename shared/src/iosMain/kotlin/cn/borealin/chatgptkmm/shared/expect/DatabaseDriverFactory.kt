package cn.borealin.chatgptkmm.shared.expect

import cn.borealin.chatgptkmm.shared.cache.db.AppDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(AppDatabase.Schema, "chatgpt_kmm.db")
    }
}