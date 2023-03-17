import org.gradle.kotlin.dsl.kotlin
import org.gradle.plugin.use.PluginDependenciesSpec

object Plugins {
    // Kotlin
    const val kotlinAndroid = "android"
    const val kotlinMultiplatform = "multiplatform"
    const val kotlinSerialization = "plugin.serialization"
    const val ktlint = "org.jlleitschuh.gradle.ktlint"
    const val detekt = "io.gitlab.arturbosch.detekt"

    // KMM
    const val sqlDelight = "com.squareup.sqldelight"
    const val nativeCoroutines = "com.rickclephas.kmp.nativecoroutines"
    const val kotlinxTestResources = "com.goncalossilva.resources"

    // Android
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"

    // iOS
    const val cocoapods = "native.cocoapods"
}