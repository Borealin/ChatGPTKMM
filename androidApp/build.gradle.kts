import version.gradle.Depends

plugins {
    id("com.android.application")
    kotlin("android")
    id("version.gradle")
}

android {
    namespace = "cn.borealin.chatgptkmm.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "cn.borealin.chatgptkmm.android"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0"
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
    implementation(Depends.composeUI)
    implementation(Depends.composeUITooling)
    implementation(Depends.composeUIToolingPreview)
    implementation(Depends.composeFoundation)
    implementation(Depends.composeMaterial)
    implementation(Depends.composeActivity)
}