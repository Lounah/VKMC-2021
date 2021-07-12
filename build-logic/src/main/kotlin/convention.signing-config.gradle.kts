import com.android.build.gradle.BaseExtension

configure<BaseExtension> {
    signingConfigs {
        getByName("debug") {
            storeFile(File("${project.rootProject.rootDir}/store/cert"))
            storePassword("password")
            keyAlias("some-key")
            keyPassword("password")
        }
        register("release") {
            storeFile(File("${project.rootProject.rootDir}/store/cert"))
            storePassword("password")
            keyAlias("some-key")
            keyPassword("password")
        }
    }
}
