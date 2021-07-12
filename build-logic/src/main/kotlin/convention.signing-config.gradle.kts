import com.android.build.gradle.BaseExtension

configure<BaseExtension> {
    signingConfigs {
        getByName("debug") {
            storeFile(File("${project.rootProject.rootDir}/keystore/release.keystore"))
            storePassword("password")
            keyAlias("vkmc-2021")
            keyPassword("password")
        }
        register("release") {
            storeFile(File("${project.rootProject.rootDir}/keystore/release.keystore"))
            storePassword("password")
            keyAlias("vkmc-2021")
            keyPassword("password")
        }
    }
}
