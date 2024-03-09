plugins {
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
}

dependencies {
    implementation(project(":core"))
    implementation(libs.coroutines.core)
}