
import kotlinx.kover.gradle.plugin.dsl.AggregationType
import kotlinx.kover.gradle.plugin.dsl.MetricType
import org.jetbrains.dokka.DokkaConfiguration
import org.jetbrains.dokka.base.DokkaBase
import org.jetbrains.dokka.base.DokkaBaseConfiguration
import org.jetbrains.dokka.gradle.DokkaTask
import java.net.URL

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.kover)
    alias(libs.plugins.dokka)
    `maven-publish`
    signing
}

buildscript {
    dependencies {
        classpath(libs.dokka.base)
    }
}

group = "io.github.rubenquadros"
version = "0.0.1"

kotlin {
    applyDefaultHierarchyTemplate()
    jvm()
    androidTarget {
        publishLibraryVariants("release")
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                //ktor
                implementation(libs.bundles.ktor)

                //coroutines
                implementation(libs.coroutines.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test.junit)

                //ktor
                implementation(libs.ktor.test)
                //coroutines
                implementation(libs.coroutines.test)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(libs.ktor.java)
                implementation(libs.ktor.client.logging)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.android)
                implementation(libs.ktor.client.logging)
            }
        }

        val androidUnitTest by getting {
            dependencies {
                implementation(libs.okio)
            }
        }
    }
}

android {
    namespace = "io.github.rubenquadros.kovibes.api"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

koverReport {
    filters {
        excludes {
            classes(
                "io.github.rubenquadros.kovibes.response.*",
                "io.github.rubenquadros.kovibes.request.*",
                "io.github.rubenquadros.kovibes.api.config.*"
            )
            annotatedBy("io.github.rubenquadros.kovibes.api.ExcludeFromCoverage")
        }
    }

    verify {
        rule {
            isEnabled = true

            filters {
                excludes {
                    classes(
                        "io.github.rubenquadros.kovibes.response.*",
                        "io.github.rubenquadros.kovibes.request.*",
                        "io.github.rubenquadros.kovibes.api.config.*"
                    )
                    annotatedBy("io.github.rubenquadros.kovibes.api.ExcludeFromCoverage")
                }
            }

            bound {
                minValue = 82
                metric = MetricType.LINE
                aggregation = AggregationType.COVERED_PERCENTAGE
            }
        }
    }
}

tasks.withType<DokkaTask>().configureEach {
    pluginConfiguration<DokkaBase, DokkaBaseConfiguration> {
        footerMessage = "Copyright © 2024 KoVibes"
    }

    dokkaSourceSets {
        named("commonMain") {
            moduleName.set("KoVibes")
            includes.from("api.md")
            sourceRoots.from(file("src/commonMain"))
            skipEmptyPackages.set(true)
            documentedVisibilities.set(
                setOf(DokkaConfiguration.Visibility.PUBLIC, DokkaConfiguration.Visibility.INTERNAL)
            )

            sourceLink {
                localDirectory.set(file("src/commonMain/kotlin"))
                remoteUrl.set(
                    URL("https://github.com/rubenquadros/KoVibes/tree/main/core/src/commonMain/kotlin")
                )
                remoteLineSuffix.set("#L")
            }
        }
    }
}

publishing {
    val javadocJar = tasks.register<Jar>("javadocJar") {
        dependsOn(tasks.dokkaHtml)
        archiveClassifier.set("javadoc")
        from("${layout.buildDirectory}/dokka")
    }

    repositories {
        maven {
            val url = if (project.version.toString().endsWith("SNAPSHOT")) {
                "https://s01.oss.sonatype.org/content/repositories/snapshots/"
            } else {
                "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
            }
            name = "KoVibes"
            setUrl(url)
            credentials {
                username = System.getenv("SONATYPE_USERNAME")
                password = System.getenv("SONATYPE_PASSWORD")
            }
        }
    }

    publications {
        withType<MavenPublication> {
            artifact(javadocJar)

            version = project.version.toString()

            pom {
                name.set(rootProject.name)
                description.set("A Kotlin Multiplatform library which is a wrapper around the Spotify web API.")
                url.set("https://github.com/rubenquadros/KoVibes")

                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }

                developers {
                    developer {
                        name.set("Ruben Quadros")
                        id.set("rubenquadros")
                        email.set("rquadros95@gmail.com")
                    }
                }

                issueManagement {
                    system.set("Github")
                    url.set("https://github.com/rubenquadros/KoVibes/issues")
                }

                scm {
                    url.set("https://github.com/rubenquadros/KoVibes")
                }
            }
        }
    }
}

signing {
    val key = System.getenv("SIGNING_KEY")
    val password = System.getenv("SIGNING_PASSWORD")

    if (!key.isNullOrEmpty()) {
        useInMemoryPgpKeys(key, password)
        publishing.publications {
            sign(publishing.publications)
        }
    }
}

// Workaround for gradle issue: https://github.com/gradle/gradle/issues/26091
tasks.withType<AbstractPublishToMaven>().configureEach {
    val signingTasks = tasks.withType<Sign>()
    mustRunAfter(signingTasks)
}