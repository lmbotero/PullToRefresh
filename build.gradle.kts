import org.jlleitschuh.gradle.ktlint.KtlintExtension

plugins {
    alias(libs.plugins.multiplatform).apply(false)
    alias(libs.plugins.compose.compiler).apply(false)
    alias(libs.plugins.compose).apply(false)
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.android.library).apply(false)
    alias(libs.plugins.jlleitschuh.ktlint)
    id("dokka-convention")
}

subprojects {
    apply(plugin = "dokka-convention")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    configure<KtlintExtension> {
        version.set("1.5.0")
        enableExperimentalRules.set(true)
    }
}

dependencies {
    dokka(project(":sample:composeApp"))
    dokka(project(":pullToRefresh"))
}
