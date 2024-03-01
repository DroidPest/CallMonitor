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

apply {
    from(rootProject.file("$rootDir/config/detekt/detekt.gradle"))
}

tasks.register("detektProjectBaseline", DetektCreateBaselineTask::class) {
    description = "Overrides detekt baseline"

    setSource(files(rootDir))
    config.setFrom(file("$rootDir/config/detekt/detekt.yml"))
    baseline.set(file("$rootDir/config/detekt/baseline.xml"))

    ignoreFailures.set(true)
    parallel.set(true)
    buildUponDefaultConfig.set(true)
    include("**/*.kt")
    include("**/*.kts")
    exclude("**/resources/**")
    exclude("**/build/**")
}

tasks.getByPath(":app:preBuild").dependsOn(":installGitHook")

tasks.register("installGitHook", Copy::class) {
    exec {
        commandLine("cp", "./config/scripts/pre-commit", "./.git/hooks")
    }
}

tasks.register("clean", Delete::class) {
    delete (rootProject.buildDir)
}
