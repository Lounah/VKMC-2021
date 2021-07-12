@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.BaseExtension

plugins {
    id("convention.signing-config")
    id("convention.packaging-options")
}

configure<BaseExtension> {
    compileSdkVersion(versions.compileSdk)

    defaultConfig {
        minSdkVersion(versions.minSdk)
        targetSdkVersion(versions.targetSdk)
        ndk {
            abiFilters.addAll(arrayOf("armeabi-v7a", "arm64-v8a", "x86", "x86_64"))
        }
    }
    buildFeatures.viewBinding = true

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    sourceSets.getByName("main") {
        java.srcDir("src/main/kotlin")
    }

    testOptions {
        unitTests {
            isReturnDefaultValues = true
        }
    }
}

dependencies {
    add("implementation", deps.viewBindingDelegate)
}