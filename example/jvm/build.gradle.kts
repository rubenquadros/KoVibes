plugins {
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
}

dependencies {
    implementation(project(":kovibes"))
    implementation(libs.coroutines.core)
}