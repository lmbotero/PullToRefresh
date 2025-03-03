plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    val dokkaPluginVersion = "2.0.0"
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:$dokkaPluginVersion")
}
