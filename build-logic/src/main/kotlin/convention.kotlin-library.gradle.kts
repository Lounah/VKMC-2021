import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("kotlin")
    `java-library`
    `maven-publish`
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
    withType<Test> {
        testLogging.showStandardStreams = true
        useJUnitPlatform()
    }
}
