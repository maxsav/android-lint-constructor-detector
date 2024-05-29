plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.lint_k2_constructor_call"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.lint_k2_constructor_call"
        minSdk = 24
        targetSdk = 34
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    lint {
        checkOnly.add("ConstructorCallDetectorIssue")
    }
}

tasks.named { it == "lintAnalyzeDebug" }.configureEach {
    outputs.cacheIf { false } // for logs via println
    outputs.upToDateWhen { false } // for logs via println
}

dependencies {
    implementation(project(":mylibrary"))
    lintChecks(project(":custom-lint"))
}