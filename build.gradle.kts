import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType
import org.jetbrains.intellij.platform.gradle.models.ProductRelease
import org.jetbrains.intellij.platform.gradle.tasks.VerifyPluginTask

fun property(name: String) = providers.gradleProperty(name)

plugins {
    id("org.jetbrains.intellij.platform")
}

group = property("pluginGroup").get()
version = property("pluginVersion").get()

dependencies {
    intellijPlatform {
        when (property("platformType").get()) {
            "IC" -> intellijIdeaCommunity(property("platformVersion"))
            "IU" -> intellijIdea(property("platformVersion"))
            else -> error("Unsupported platformType. Expected IC or IU.")
        }
    }
}

intellijPlatform {
    buildSearchableOptions.set(false)
    instrumentCode.set(false)

    pluginConfiguration {
        id.set(property("pluginGroup"))
        name.set(property("pluginName"))
        version.set(property("pluginVersion"))

        description.set(
            """
            <p>Apricode Monokai is a dark theme for IntelliJ Platform IDEs that combines the familiar Monokai structure with Apricode brand colors.</p>
            <ul>
              <li>Dedicated Go highlighting for declarations, calls, types, packages, constants, tags, and variable scopes.</li>
              <li>Readable Git, diff, breakpoint, banner, and notification states.</li>
              <li>Custom Apricode Build, Run, and Debug icons.</li>
            </ul>
            """.trimIndent(),
        )

        changeNotes.set(
            """
            <ul>
              <li>Recolored indeterminate progress indicators with the Apricode orange gradient.</li>
              <li>Replaced the blue memory usage gauge with an Apricode orange state.</li>
              <li>Applied Apricode colors to toolbar badges and plugin restart actions.</li>
            </ul>
            """.trimIndent(),
        )

        ideaVersion {
            sinceBuild.set(property("pluginSinceBuild"))
            property("pluginUntilBuild").orNull
                ?.takeIf(String::isNotBlank)
                ?.let(untilBuild::set)
                ?: untilBuild.set(provider { null })
        }

        vendor {
            name.set("Apricode Team")
            email.set("support@apricode.am")
            url.set("https://web.apricode.am")
        }
    }

    pluginVerification {
        failureLevel.set(
            listOf(
                VerifyPluginTask.FailureLevel.COMPATIBILITY_PROBLEMS,
                VerifyPluginTask.FailureLevel.DEPRECATED_API_USAGES,
                VerifyPluginTask.FailureLevel.SCHEDULED_FOR_REMOVAL_API_USAGES,
                VerifyPluginTask.FailureLevel.INTERNAL_API_USAGES,
                VerifyPluginTask.FailureLevel.OVERRIDE_ONLY_API_USAGES,
                VerifyPluginTask.FailureLevel.NON_EXTENDABLE_API_USAGES,
                VerifyPluginTask.FailureLevel.PLUGIN_STRUCTURE_WARNINGS,
                VerifyPluginTask.FailureLevel.MISSING_DEPENDENCIES,
                VerifyPluginTask.FailureLevel.INVALID_PLUGIN,
            ),
        )

        ides {
            property("pluginVerifierCommunityVersions").getOrElse("")
                .split(',')
                .map(String::trim)
                .filter(String::isNotEmpty)
                .forEach { create(IntelliJPlatformType.IntellijIdeaCommunity, it) }

            property("pluginVerifierIdeaVersions").getOrElse("")
                .split(',')
                .map(String::trim)
                .filter(String::isNotEmpty)
                .forEach { create(IntelliJPlatformType.IntellijIdea, it) }

            property("pluginVerifierEapBuild").orNull
                ?.takeIf(String::isNotBlank)
                ?.let { buildBranch ->
                    select {
                        types = listOf(IntelliJPlatformType.IntellijIdea)
                        channels = listOf(ProductRelease.Channel.EAP)
                        sinceBuild = buildBranch
                        untilBuild = "$buildBranch.*"
                    }
                }
        }
    }

    publishing {
        token.set(providers.environmentVariable("PUBLISH_TOKEN"))
        channels.set(listOf("default"))
    }
}
