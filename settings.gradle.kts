import org.jetbrains.intellij.platform.gradle.extensions.intellijPlatform

rootProject.name = "apricode-monokai"

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }

}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
    id("org.jetbrains.intellij.platform.settings") version "2.18.1"
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositories {
        mavenCentral()
        intellijPlatform {
            defaultRepositories()
        }
    }
}
