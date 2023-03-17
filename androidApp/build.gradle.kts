plugins {
    id(Plugins.androidApplication)
    kotlin(Plugins.kotlinAndroid)
}

android {
    namespace = AndroidSdk.nameSpace
    compileSdk = AndroidSdk.compileSdkVersion
    defaultConfig {
        applicationId = AndroidSdk.applicationId
        minSdk = AndroidSdk.minSdkVersion
        targetSdk = AndroidSdk.targetSdkVersion
        versionCode = AndroidSdk.versionCode
        versionName = AndroidSdk.versionName
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":shared"))

    implementation(AndroidDependencies.kotlinStdlib)
    implementation(AndroidDependencies.androidCore)
    implementation(AndroidDependencies.appCompat)

    val composeBom = platform(AndroidDependencies.composeBom)
    implementation(composeBom)
    implementation(AndroidDependencies.composeFoundation)
    implementation(AndroidDependencies.composeUI)
    implementation(AndroidDependencies.composeUIToolingPreview)
    implementation(AndroidDependencies.composeMaterial3)
    implementation(AndroidDependencies.composeMaterial)
    implementation(AndroidDependencies.composeMaterialIconsExtended)
    implementation(AndroidDependencies.composeMaterial3WindowSizeClass)
    implementation(AndroidDependencies.activityCompose)
    implementation(AndroidDependencies.composeMarkdown)
    implementation(AndroidDependencies.coilCompose)

    implementation(AndroidDependencies.accompanistInsets)
    implementation(AndroidDependencies.accompanistSystemUIController)
    implementation(AndroidDependencies.accompanistPager)
    implementation(AndroidDependencies.accompanistPagerIndicators)
    implementation(AndroidDependencies.accompanistPlaceholderMaterial)
    implementation(AndroidDependencies.accompanistNavigationAnimation)

    implementation(AndroidDependencies.navigationCompose)

    implementation(AndroidDependencies.lifecycleRuntime)

    implementation(AndroidDependencies.koinAndroid)
    implementation(AndroidDependencies.koinCompose)


    androidTestImplementation(composeBom)
    debugImplementation(AndroidDependencies.composeUITooling)
    androidTestImplementation(AndroidDependencies.composeUITestJunit4)
    debugImplementation(AndroidDependencies.composeUITestManifest)
    debugImplementation(AndroidDependencies.leakCanary)
}