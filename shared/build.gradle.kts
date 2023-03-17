plugins {
    kotlin(Plugins.kotlinMultiplatform)
    kotlin(Plugins.kotlinSerialization)
    kotlin(Plugins.cocoapods)
    id(Plugins.androidLibrary)
    id(Plugins.nativeCoroutines)
    id(Plugins.sqlDelight)
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(SharedCommonMainDependencies.kotlinxCoroutines)
                implementation(SharedCommonMainDependencies.kotlinxSerialization)
                implementation(SharedCommonMainDependencies.kotlinxSerializationJSON)
                implementation(SharedCommonMainDependencies.kotlinxDatetime)

                implementation(SharedCommonMainDependencies.ktorCore)
                implementation(SharedCommonMainDependencies.ktorLogging)
                implementation(SharedCommonMainDependencies.ktorContentNegotiation)
                implementation(SharedCommonMainDependencies.ktorSerializationKotlinxJSON)

                implementation(SharedCommonMainDependencies.sqlDelight)
                implementation(SharedCommonMainDependencies.sqlDelightCoroutine)

                implementation(SharedCommonMainDependencies.multiplatformSettings)
                implementation(SharedCommonMainDependencies.multiplatformSettingsCoroutines)

                api(SharedCommonMainDependencies.napier)

                api(SharedCommonMainDependencies.koinCore)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(SharedCommonTestDependencies.ktorMock)
                implementation(SharedCommonTestDependencies.multiplatformSettingsTest)
                implementation(SharedCommonTestDependencies.kotlinxTestResources)
                implementation(SharedCommonTestDependencies.kotlinxCoroutinesTest)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(SharedAndroidMainDependencies.ktorClientAndroid)
                implementation(SharedAndroidMainDependencies.sqlDelightAndroidDriver)
            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(SharedIosMainDependencies.ktorClientDarwin)
                implementation(SharedIosMainDependencies.sqlDelightNativeDriver)
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = SharedAndroidSdk.namespace
    compileSdk = SharedAndroidSdk.compileSdk
    defaultConfig {
        minSdk = SharedAndroidSdk.minSdk
        targetSdk = SharedAndroidSdk.targetSdk
    }
}

sqldelight {
    database(SharedSQLDelight.databaseName) {
        packageName = SharedSQLDelight.packageName
    }
}