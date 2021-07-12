@file:Suppress("unused")

@Suppress("ClassName")
object versions {
    @JvmStatic
    val applicationId get() = "com.lounah.vkmc"

    @JvmStatic
    val versionName get() = "vkmc-2021"

    @Suppress("unused")
    val versionCode get() = 2306

    const val compileSdk = 30

    const val targetSdk = 30

    const val minSdk = 21

    const val minSdkCompat = 21

    const val viewBindingDelegate = "1.4.7"
}

object deps {
    const val viewBindingDelegate =
        "com.github.kirich1409:viewbindingpropertydelegate-noreflection:${versions.viewBindingDelegate}"
}