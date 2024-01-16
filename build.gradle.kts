// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false

    // Dagger Hilt
    id("com.google.dagger.hilt.android") version "2.50" apply false
    // Kotlin serialization
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.21"
    // KSP annotation processor plugin
    id("com.google.devtools.ksp") version "1.9.22-1.0.16" apply false
}