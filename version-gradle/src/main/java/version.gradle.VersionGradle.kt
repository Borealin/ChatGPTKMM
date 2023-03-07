@file:Suppress("unused")

package version.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class VersionGradle : Plugin<Project> {
    override fun apply(project: Project) {
    }
}

object Version {
    const val compose = "1.3.1"
    const val composeActivity = "1.6.1"
    const val coroutines = "1.6.4"
    const val ktor = "2.2.1"
    const val sqlDelight = "1.5.5"
    const val dateTime = "0.4.0"
}

object Depends {
    const val composeUI = "androidx.compose.ui:ui:${Version.compose}"
    const val composeUITooling = "androidx.compose.ui:ui-tooling:${Version.compose}"
    const val composeUIToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Version.compose}"
    const val composeFoundation = "androidx.compose.foundation:foundation:${Version.compose}"
    const val composeMaterial = "androidx.compose.material:material:${Version.compose}"
    const val composeActivity = "androidx.activity:activity-compose:${Version.composeActivity}"

    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"

    const val ktorClientCore = "io.ktor:ktor-client-core:${Version.ktor}"
    const val ktorClientContentNegotiation =
        "io.ktor:ktor-client-content-negotiation:${Version.ktor}"
    const val ktorSerializationKotlinxJSON =
        "io.ktor:ktor-serialization-kotlinx-json:${Version.ktor}"
    const val ktorClientAndroid = "io.ktor:ktor-client-android:${Version.ktor}"
    const val ktorClientDarwin = "io.ktor:ktor-client-darwin:${Version.ktor}"

    const val sqlDelightRuntime = "com.squareup.sqldelight:runtime:${Version.sqlDelight}"
    const val sqlDelightAndroidDriver =
        "com.squareup.sqldelight:android-driver:${Version.sqlDelight}"
    const val sqlDelightNativeDriver = "com.squareup.sqldelight:native-driver:${Version.sqlDelight}"
}