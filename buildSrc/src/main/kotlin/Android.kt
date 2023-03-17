import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.project
import org.gradle.plugin.use.PluginDependenciesSpec

object AndroidSdk {
    const val appName = "ChatGPTKMM"

    const val applicationId = "cn.borealin.chatgptkmm.android"
    const val nameSpace = applicationId

    const val buildToolVersion = "30.0.3"

    const val minSdkVersion = 24
    const val compileSdkVersion = 33
    const val targetSdkVersion = compileSdkVersion

    const val versionCode = 1
    const val versionName = "1.0"

    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object AndroidDependencies {
    // Core Dependencies
    const val kotlinStdlib = "${Artifacts.kotlinStdlib}:${Versions.kotlin}"
    const val androidCore = "${Artifacts.androidCore}:${Versions.androidCore}"
    const val appCompat = "${Artifacts.appCompat}:${Versions.appCompat}"

    // Compose Dependencies
    const val composeBom = "${Artifacts.composeBom}:${Versions.composeBom}"
    const val composeFoundation = Artifacts.composeFoundation
    const val composeUI = Artifacts.composeUI

    // Compose Test
    const val composeUIToolingPreview = Artifacts.composeUIToolingPreview
    const val composeUITooling = Artifacts.composeUITooling
    const val composeUITestJunit4 = Artifacts.composeUITestJunit4
    const val composeUITestManifest = Artifacts.composeUITestManifest

    // Compose Material
    const val composeMaterial3 = Artifacts.composeMaterial3
    const val composeMaterial = Artifacts.composeMaterial
    const val composeMaterialIconsExtended = Artifacts.composeMaterialIconsExtended
    const val composeMaterial3WindowSizeClass =
        Artifacts.composeMaterial3WindowSizeClass

    // Compose Third Party
    const val composeMarkdown = "${Artifacts.composeMarkdown}:${Versions.composeMarkdown}"
    const val coilCompose = "${Artifacts.coilCompose}:${Versions.coilCompose}"

    // Compose Android
    const val activityCompose = "${Artifacts.activityCompose}:${Versions.activityCompose}"

    // Accompanist Dependencies
    const val accompanistInsets = "${Artifacts.accompanistInsets}:${Versions.accompanist}"
    const val accompanistSystemUIController =
        "${Artifacts.accompanistSystemUIController}:${Versions.accompanist}"
    const val accompanistPager = "${Artifacts.accompanistPager}:${Versions.accompanist}"
    const val accompanistPagerIndicators =
        "${Artifacts.accompanistPagerIndicators}:${Versions.accompanist}"
    const val accompanistPlaceholderMaterial =
        "${Artifacts.accompanistPlaceholderMaterial}:${Versions.accompanist}"
    const val accompanistNavigationAnimation =
        "${Artifacts.accompanistNavigationAnimation}:${Versions.accompanist}"

    // Navigation Dependencies
    const val navigationCompose = "${Artifacts.navigationCompose}:${Versions.navigationCompose}"

    // Lifecycle Dependencies
    const val lifecycleRuntime = "${Artifacts.lifecycleRuntime}:${Versions.lifecycleRuntime}"

    // Koin Dependencies
    const val koinAndroid = "${Artifacts.koinAndroid}:${Versions.koinAndroid}"
    const val koinCompose = "${Artifacts.koinCompose}:${Versions.koinCompose}"

    // Debug Dependencies
    const val leakCanary = "${Artifacts.leakCanary}:${Versions.leakCanary}"

}