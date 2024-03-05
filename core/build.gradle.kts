import kotlinx.kover.gradle.plugin.dsl.AggregationType
import kotlinx.kover.gradle.plugin.dsl.MetricType

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.kover)
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
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.android)
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