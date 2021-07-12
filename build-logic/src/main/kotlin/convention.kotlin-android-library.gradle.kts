@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.BaseExtension

plugins {
    id("com.android.library")
    id("convention.android-common")
}

configure<BaseExtension> {
    resourcePrefix = project.name
        .replace("feature-", "")
        .replace("common-", "")
        .replace("-", "_")
        .plus("_")

    buildFeatures.buildConfig = true

    variantFilter {
        if (buildType.name == "debug") {
            ignore = true
        }
    }
}
