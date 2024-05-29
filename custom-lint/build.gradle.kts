plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

dependencies {
    val (major, minor, patch) = libs.versions.agp.get().split('.')
    val lintVersion = "${major.toInt() + 23}.$minor.$patch"
    // For a description of the below dependencies, see the main project README
    compileOnly("com.android.tools.lint:lint-api:$lintVersion")
    compileOnly("com.android.tools.lint:lint-checks:$lintVersion")
}