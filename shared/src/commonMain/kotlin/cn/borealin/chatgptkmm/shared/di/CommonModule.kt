package cn.borealin.chatgptkmm.shared.di

import cn.borealin.chatgptkmm.shared.Constants
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import cn.borealin.chatgptkmm.shared.cache.settings.PreferenceManager
import cn.borealin.chatgptkmm.shared.repo.ChatRepository
import cn.borealin.chatgptkmm.shared.repo.IChatRepository
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.addDefaultResponseValidation
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import cn.borealin.chatgptkmm.shared.vm.SharedChatVM
import cn.borealin.chatgptkmm.shared.net.ChatApi
import io.ktor.client.plugins.HttpTimeout
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.core.module.dsl.factoryOf

@OptIn(ExperimentalSerializationApi::class)
fun commonModule(enableNetworkLogs: Boolean) = module {
    // Net
    single {
        HttpClient {
            expectSuccess = true
            addDefaultResponseValidation()

            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = Constants.BASE_URL
                    path(Constants.DEFAULT_PATH)
                    get<PreferenceManager>().apiKey.takeIf {
                            it.isNotBlank()
                        }?.let {
                            headers.append("Authorization", "Bearer $it")
                        }
                }
            }
            if (enableNetworkLogs) {
                install(Logging) {
                    level = LogLevel.HEADERS
                    logger = object : Logger {
                        override fun log(message: String) {
                            Napier.e(tag = "Http Client", message = message)
                        }
                    }
                }
            }
            install(ContentNegotiation) {
                json(Json {
                    explicitNulls = false
                    ignoreUnknownKeys = true
                    isLenient = true
                    useAlternativeNames = false
                })
            }
            install(HttpTimeout) {
                requestTimeoutMillis = Long.MAX_VALUE
                connectTimeoutMillis = Long.MAX_VALUE
                socketTimeoutMillis = Long.MAX_VALUE
            }
        }
    }
    singleOf(::ChatApi)

    // Cache
    singleOf(::PreferenceManager)

    single<IChatRepository> { ChatRepository(get()) }
    factoryOf(::SharedChatVM)
}
