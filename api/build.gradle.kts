plugins {
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.serialization") version "1.9.22"
}

group = "com.ruben"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    //ktor
    val ktorVersion = "2.3.8"
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-auth:$ktorVersion")
    implementation("io.ktor:ktor-client-java:$ktorVersion")
    implementation("io.ktor:ktor-client-logging:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:${ktorVersion}")
    testImplementation("io.ktor:ktor-client-mock:$ktorVersion")

    //coroutines
    val coroutinesVersion = "1.8.0"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}