plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.org.jetbrains.kotlin)
    alias(libs.plugins.hilt.plugin)
    id("org.jetbrains.kotlin.kapt")
}

val baseUrl: String = (System.getenv("BASE_URL") ?: providers.gradleProperty("baseUrlProperties").get())

android {
    namespace = "com.mk.networking"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        buildConfigField("String", "BASE_URL", "\"$baseUrl\"")

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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/io.netty.versions.properties"
            excludes += "META-INF/INDEX.LIST"
        }
    }
    buildFeatures {
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
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

    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.work.runtime)

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.compose)
    implementation(libs.androidx.hilt.work)
    kapt(libs.hilt.compiler)
    kapt(libs.androidx.hilt.compiler)
    annotationProcessor(libs.androidx.hilt.compiler)
    implementation(libs.runtime.ktx)

    // Network
    implementation(libs.ktor.netty)
    implementation(libs.ktor.core)
    implementation(libs.ktor.content.negotiation)
    implementation(libs.ktor.gson)
    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.gson.converter)

    // Logging
    implementation(libs.timber)
    implementation(libs.http.logging)

    // Tests
    testImplementation(libs.junit.android.test)
    androidTestImplementation(libs.androidx.ext.junit.test)
    androidTestImplementation(libs.androidx.espresso.core)
}