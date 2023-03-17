package cn.borealin.chatgptkmm.shared.cache.settings

import cn.borealin.chatgptkmm.shared.Constants
import com.russhwolf.settings.ObservableSettings

class PreferenceManager constructor(private val observableSettings: ObservableSettings) {
    var apiKey: String
        set(value) = observableSettings.putString(API_KEY, value)
        get() = observableSettings.getString(API_KEY, Constants.API_KEY)

    fun clearPreferences() = observableSettings.clear()

    companion object {
        private const val API_KEY = "api_key"
    }
}