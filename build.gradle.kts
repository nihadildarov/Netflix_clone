// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        mavenCentral()
    }

    dependencies{

        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.6")
    }
}
plugins {
    id("com.android.application") version "8.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false
    id("org.jetbrains.kotlin.kapt") version "1.9.21" apply false
    //id("org.jetbrains.kotlin.ksp") version "1.9.21" apply  false
    id("com.google.dagger.hilt.android") version "2.48" apply false

}