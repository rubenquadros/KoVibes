
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
}

buildscript {
    dependencies {
        classpath(libs.dokka.base)
    }
}

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
    namespace = "com.ruben.spotify.api"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

koverReport {
    filters {
        excludes {
            classes(
                "com.ruben.spotify.api.response.*",
                "com.ruben.spotify.api.request.*"
            )
            annotatedBy("com.ruben.spotify.api.ExcludeFromCoverage")
        }
    }

    verify {
        rule {
            isEnabled = true
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
        footerMessage = "Copyright © 2024 KoSpotify"
    }

    dokkaSourceSets {
        named("commonMain") {
            moduleName.set("KoSpotify")
            includes.from("api.md")
            sourceRoots.from(file("src/commonMain"))
            skipEmptyPackages.set(true)
            documentedVisibilities.set(
                setOf(DokkaConfiguration.Visibility.PUBLIC, DokkaConfiguration.Visibility.INTERNAL)
            )

            sourceLink {
                localDirectory.set(file("src/commonMain/kotlin"))
                remoteUrl.set(
                    URL("https://github.com/rubenquadros/KoSpotify/tree/main/core/src/commonMain/kotlin")
                )
                remoteLineSuffix.set("#L")
            }
        }
    }
}