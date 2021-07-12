import com.android.build.gradle.BaseExtension

configure<BaseExtension> {
    packagingOptions {
        excludes.addAll(
            listOf(
                "META-INF/LICENSE.txt",
                "META-INF/LICENSE.md",
                "META-INF/LICENSE-notice.md",
                "META-INF/NOTICE.txt",
                "META-INF/DEPENDENCIES",
                "META-INF/services/javax.annotation.processing.Processor"
            )
        )
        pickFirsts.addAll(
            listOf(
                "lib/x86_64/libRSSupport.so",
                "lib/x86/libRSSupport.so",
                "lib/armeabi-v7a/libRSSupport.so",
                "lib/arm64-v8a/libRSSupport.so",
                "lib/x86/librsjni.so",
                "lib/arm64-v8a/librsjni.so",
                "lib/armeabi-v7a/librsjni.so",
                "lib/x86_64/librsjni.so",
                "lib/x86_64/librsjni_androidx.so",
                "lib/x86/librsjni_androidx.so",
                "lib/arm64-v8a/librsjni_androidx.so",
                "lib/armeabi-v7a/librsjni_androidx.so"
            )
        )
    }
}
