import com.android.build.gradle.BaseExtension

plugins {
    id("convention.kotlin-android-library")
}

configure<BaseExtension> {
    resourcePrefix = ""
}

dependencies {
    api(libraries.appcompat)
    api(libraries.design)
    api(libraries.ktx)
    api(libraries.fragment)
    api(libraries.recycler)
}