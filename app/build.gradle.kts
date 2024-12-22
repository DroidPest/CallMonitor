import io.gitlab.arturbosch.detekt.Detekt
import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.org.jetbrains.kotlin)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.io.gitlab.arturbosch.detekt)
    id("org.jetbrains.kotlin.kapt")
}

val keyStorePropertiesFile = rootProject.file("keystore.properties")
val keyStoreProperties = Properties()
if (keyStorePropertiesFile.exists()) {
    keyStoreProperties.load(FileInputStream(keyStorePropertiesFile))
}

val versionMajor = 1
val versionMinor = 0
val versionPatch = 0
val versionBuild = (System.getenv("BUILD_NUM") ?: "9999").toInt()

detekt {
    autoCorrect = true
    buildUponDefaultConfig = true
    allRules = false
    config.setFrom("$rootDir/config/detekt/detekt.yml")
    baseline = file("$rootDir/config/detekt/baseline.xml")
}
tasks.withType<Detekt>().configureEach {
    jvmTarget = JavaVersion.VERSION_17.toString()
}

android {
    namespace = "com.mk.assessment"
    compileSdkVersion(libs.versions.compileSdk.get().toInt())

    defaultConfig {
        applicationId = "com.mk.assessment"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = versionMajor * 10000 + versionMinor * 1000 + versionPatch * 100 + versionBuild
        versionName = "assessment-${versionMajor}.${versionMinor}.${versionPatch}"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
//        create("devDebug") {
//            storeFile = file("$rootDir/config/keystore/debug/debug.keystore.jks")
//            storePassword = "android"
//            keyAlias = "androiddebugkey"
//            keyPassword = "android"
//        }
        create("release") {
            if (keyStorePropertiesFile.exists()) {
                keyAlias = keyStoreProperties["androidReleaseKeyAlias"] as String
                keyPassword = keyStoreProperties["androidReleaseKeyPassword"] as String
                storeFile = project.properties["androidReleaseKeyStore"]?.let { file(it) }
                storePassword = keyStoreProperties["androidReleaseStorePassword"] as String
            }
        }
    }

    buildTypes {
        debug {
            isDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
//            signingConfig = signingConfigs.getByName("devDebug")
        }
        create("staging") {
            isDebuggable = true
            isMinifyEnabled = true
            isShrinkResources = true
            applicationIdSuffix = ".stg"
            versionNameSuffix = "-stg"
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "retrofit-okhttp.pro", "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }
        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "retrofit-okhttp.pro", "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinCompilerExtensionVersion.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/io.netty.versions.properties"
            excludes += "META-INF/INDEX.LIST"
        }
    }
    lint {
        abortOnError = true
        ignoreWarnings = false
        warningsAsErrors = false
        checkDependencies = false
        baseline = file("../lint-baseline.xml")
        lintConfig = file("../assessment-lint.xml")
    }
}

dependencies {
    implementation(project(path = ":infrastructure"))
    implementation(project(path = ":ui-library"))
    implementation(project(path = ":local-storage"))
    implementation(project(path = ":networking"))

    implementation(libs.androidx.core)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.runtime.ktx)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.splash.screen)
    implementation(libs.androidx.paging.source)
    implementation(libs.androidx.paging.compose)
    implementation(libs.core)

    debugImplementation(libs.androidx.compose.ui.tooling)

    // Network
    implementation(libs.retrofit)
    implementation(libs.okhttp)

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.compose)
    implementation(libs.androidx.hilt.work)
    kapt(libs.hilt.compiler)
    kapt(libs.androidx.hilt.compiler)
    annotationProcessor(libs.androidx.hilt.compiler)

    // Logging
    implementation(libs.timber)

    // Detekt
    detektPlugins(libs.detekt.formatting)

    // Tests
    testImplementation(libs.junit.android.test)
    androidTestImplementation(libs.androidx.ext.junit.test)
    androidTestImplementation(libs.androidx.espresso.core)
}