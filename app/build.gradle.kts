import com.android.build.gradle.BaseExtension

plugins {
    id("convention.android-application")
}

configure<BaseExtension> {
    defaultConfig {
        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
}

dependencies {
    implementation(project(":sources:core:core-ui"))
}
