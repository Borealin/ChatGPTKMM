package cn.borealin.chatgptkmm.shared.expect

import android.content.Context
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.SharedPreferencesSettings

actual class MultiplatformSettingsWrapper(private val context: Context) {
    actual fun createSettings(): ObservableSettings {
        val sharedPreferences = context.getSharedPreferences("chatgpt_kmm_preferences", Context.MODE_PRIVATE)
        return SharedPreferencesSettings(sharedPreferences)
    }
}