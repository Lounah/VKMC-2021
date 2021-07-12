@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.BaseExtension

plugins {
    id("com.android.application")
    id("convention.android-common")
    id("convention.signing-config")
}

configure<BaseExtension> {
    defaultConfig {
        applicationId(versions.applicationId)
        versionCode(versions.versionCode)
        versionName(versions.versionName)
        resConfigs("ru")
    }

    buildTypes {
        getByName("release") {
            debuggable(false)
            signingConfig = signingConfigs.getByName("release")
            isShrinkResources = true
            isMinifyEnabled = true
        }
        getByName("debug") {
            debuggable(true)
            signingConfig = signingConfigs.getByName("debug")
            isShrinkResources = false
            isMinifyEnabled = false
            applicationIdSuffix(".dev")
            setMatchingFallbacks("release")
        }
    }

    splits {
        abi {
            isEnable = true
            reset()
            include("armeabi-v7a", "arm64-v8a", "x86", "x86_64")
            isUniversalApk = true
        }
    }
}
