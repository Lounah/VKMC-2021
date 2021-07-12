@file:Suppress("UnstableApiUsage")

enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }
    resolutionStrategy {
        eachPlugin {
            val pluginId = requested.id.id
            when  {
                pluginId.startsWith("org.jetbrains.kotlin") -> useVersion("1.5.20")
                pluginId.startsWith("com.android.") -> {
                    useModule("com.android.tools.build:gradle:4.1.2")
                }
            }
        }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)

    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }

    versionCatalogs {
        create("libraries") {
            from(files("gradle/libs.versions.toml"))
        }
    }
}


rootProject.name = "VKMC-2021"

includeBuild("build-logic")
include(":app")
