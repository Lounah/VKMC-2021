plugins {
    id("convention.kotlin-android-library")
}

dependencies {
    api(libraries.lifecycleCommon)
    api(libraries.lifecycleCommonJava8)
    api(libraries.rxJava2)
    implementation(libraries.fragment)
    implementation(libraries.rxRelay2)
    implementation(libraries.rxKotlin)
    implementation(libraries.rxAndroid2)
}
