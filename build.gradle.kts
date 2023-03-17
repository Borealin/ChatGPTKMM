plugins {
    kotlin(Plugins.kotlinAndroid).version(Versions.kotlin).apply(false)
    kotlin(Plugins.kotlinMultiplatform).version(Versions.kotlin).apply(false)
    kotlin(Plugins.kotlinSerialization).version(Versions.kotlin).apply(false)
    id(Plugins.ktlint).version(Versions.ktlint).apply(false)
    id(Plugins.detekt).version(Versions.detekt).apply(false)

    id(Plugins.sqlDelight).version(Versions.sqlDelight).apply(false)
    id(Plugins.nativeCoroutines).version(Versions.nativeCoroutines).apply(false)
    id(Plugins.kotlinxTestResources).version(Versions.kotlinxTestResources).apply(false)


    id(Plugins.androidApplication).version(Versions.gradle).apply(false)
    id(Plugins.androidLibrary).version(Versions.gradle).apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
