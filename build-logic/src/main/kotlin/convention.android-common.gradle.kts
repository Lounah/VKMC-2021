@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.BaseExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-android`
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
        setSourceCompatibility(JavaVersion.VERSION_1_8)
        setTargetCompatibility(JavaVersion.VERSION_1_8)
    }

    sourceSets.getByName("main") {
        java.srcDir("src/main/kotlin")
        java.srcDir("src/main/java")
    }

    testOptions {
        unitTests {
            isReturnDefaultValues = true
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    add("implementation", deps.viewBindingDelegate)
}