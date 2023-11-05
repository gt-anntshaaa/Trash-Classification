// Top-level build file where you can add configuration options common to all sub-projects/modules.

// SafeArgs - Navigation Components
buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath("com.google.gms:google-services:4.4.0")
        val nav_version = "2.5.3"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
    }
}

plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false

    // DI Hilt
    id("com.google.dagger.hilt.android") version "2.48" apply false
}