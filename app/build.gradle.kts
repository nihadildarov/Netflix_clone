plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    //id("org.jetbrains.kotlin.ksp")

    id ("androidx.navigation.safeargs.kotlin")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")

}

android {
    namespace = "com.example.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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

    buildFeatures{
        viewBinding = true
        dataBinding = true
    }
}

dependencies {


    implementation("androidx.activity:activity-ktx:1.8.2")
    val nav_version = "2.7.7"
    val room_version = "2.6.1"



    //Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")

    //Room
    implementation ("androidx.room:room-runtime:$room_version")
    kapt ("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")

    //Picasso
    implementation("com.squareup.picasso:picasso:2.71828")
    //Retrofit
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")
    implementation("de.hdodenhof:circleimageview:3.1.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

    //Palette
    implementation("androidx.palette:palette-ktx:1.0.0")

    //FireBase
    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-firestore")
    implementation("com.google.firebase:firebase-storage")


    //YoutubeVideoPlayer
    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:chromecast-sender:0.28")


    //
    implementation("androidx.window:window-core:1.2.0")


    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    implementation("androidx.core:core-splashscreen:1.0.1")


}

kapt {
    correctErrorTypes = true
}