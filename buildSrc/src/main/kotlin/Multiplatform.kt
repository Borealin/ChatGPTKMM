object SharedBase {
    const val namespace = "cn.borealin.chatgptkmm.shared"
}

object SharedAndroidSdk {
    const val namespace = SharedBase.namespace
    const val compileSdk = 33
    const val minSdk = 24
    const val targetSdk = compileSdk
}

object SharedSQLDelight {
    const val databaseName = "AppDatabase"
    const val packageName = "${SharedBase.namespace}.cache.db"
}

object SharedCommonMainDependencies {
    const val kotlinxCoroutines = "${Artifacts.kotlinxCoroutines}:${Versions.kotlinxCoroutines}"
    const val kotlinxSerialization =
        "${Artifacts.kotlinxSerialization}:${Versions.kotlinxSerialization}"
    const val kotlinxSerializationJSON =
        "${Artifacts.kotlinxSerializationJSON}:${Versions.kotlinxSerialization}"
    const val kotlinxDatetime = "${Artifacts.kotlinxDatetime}:${Versions.kotlinxDatetime}"
    const val ktorCore = "${Artifacts.ktorCore}:${Versions.ktor}"
    const val ktorContentNegotiation =
        "${Artifacts.ktorContentNegotiation}:${Versions.ktor}"
    const val ktorSerializationKotlinxJSON =
        "${Artifacts.ktorSerializationKotlinxJSON}:${Versions.ktor}"
    const val ktorLogging = "${Artifacts.ktorLogging}:${Versions.ktor}"
    const val sqlDelight = "${Artifacts.sqlDelight}:${Versions.sqlDelight}"
    const val sqlDelightCoroutine = "${Artifacts.sqlDelightCoroutine}:${Versions.sqlDelight}"
    const val napier = "${Artifacts.napier}:${Versions.napier}"
    const val multiplatformSettings =
        "${Artifacts.multiplatformSettings}:${Versions.multiplatformSettings}"
    const val multiplatformSettingsCoroutines =
        "${Artifacts.multiplatformSettingsCoroutines}:${Versions.multiplatformSettings}"
    const val koinCore = "${Artifacts.koinCore}:${Versions.koin}"
}

object SharedCommonTestDependencies {
    const val ktorMock = "${Artifacts.ktorMock}:${Versions.ktor}"
    const val multiplatformSettingsTest =
        "${Artifacts.multiplatformSettingsTest}:${Versions.multiplatformSettings}"

    //    const val mockk = "${Artifacts.mockk}:${Versions.mockk}"
    const val kotlinxTestResources =
        "${Artifacts.kotlinxTestResources}:${Versions.kotlinxTestResources}"
    const val kotlinxCoroutinesTest =
        "${Artifacts.kotlinxCoroutinesTest}:${Versions.kotlinxCoroutines}"
}

object SharedAndroidMainDependencies {
    const val ktorClientAndroid = "${Artifacts.ktorAndroid}:${Versions.ktor}"
    const val sqlDelightAndroidDriver =
        "${Artifacts.sqlDelightAndroid}:${Versions.sqlDelight}"
}

object SharedIosMainDependencies {
    const val ktorClientDarwin = "${Artifacts.ktorDarwin}:${Versions.ktor}"
    const val sqlDelightNativeDriver = "${Artifacts.sqlDelightNative}:${Versions.sqlDelight}"
}