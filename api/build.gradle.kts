plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.org.jetbrains.kotlin)
    alias(libs.plugins.hilt.plugin)
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.mk.api"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug { }
        create("staging") { }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    lint {
        abortOnError = true
        checkDependencies = false
        checkGeneratedSources = false
        warningsAsErrors = true
        baseline = file("../lint-baseline.xml")
        lintConfig = file("../assessment-lint.xml")
    }
}

dependencies {
    implementation(project(path = ":infrastructure"))
    implementation(project(path = ":networking"))

    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.work.runtime)
    implementation(libs.androidx.paging.source)

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.compose)
    implementation(libs.androidx.hilt.work)
    kapt(libs.hilt.compiler)
    kapt(libs.androidx.hilt.compiler)
    annotationProcessor(libs.androidx.hilt.compiler)
    implementation(libs.runtime.ktx)

    // Network
    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.gson.converter)
}