import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType
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
              <li>Added complete highlighting for current Go language color keys.</li>
              <li>Improved contrast for Git, diff, breakpoint, banner, and notification states.</li>
              <li>Updated Apricode colors and custom action icons.</li>
              <li>Removed deprecated and unused template code.</li>
            </ul>
            """.trimIndent(),
        )

        ideaVersion {
            sinceBuild.set(property("pluginSinceBuild"))
            untilBuild.set(property("pluginUntilBuild"))
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
        }
    }

    publishing {
        token.set(providers.environmentVariable("PUBLISH_TOKEN"))
        channels.set(listOf("default"))
    }
}
