package cn.borealin.chatgptkmm.shared.expect

import android.content.Context
import cn.borealin.chatgptkmm.shared.cache.db.AppDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(AppDatabase.Schema, context, "chatgpt_kmm.db")
    }
}