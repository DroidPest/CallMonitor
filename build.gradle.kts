import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.org.jetbrains.kotlin) apply false
    alias(libs.plugins.io.gitlab.arturbosch.detekt) apply false
    alias(libs.plugins.hilt.plugin) apply false
    alias(libs.plugins.kotlinter) apply false
}

subprojects {
    apply {
        plugin("org.jmailen.kotlinter")
    }
}

tasks.register("clean", Delete::class) {
    delete (rootProject.buildDir)
}
