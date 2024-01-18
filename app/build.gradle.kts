plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.aitronics.jetweatherforecast"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.aitronics.jetweatherforecast"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    val lifecycle_version = "2.6.2"
    val arch_version = "2.2.0"
    val room_version = "2.6.0"

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    //Dagger - Hilt
    implementation ("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    implementation("androidx.hilt:hilt-common:1.1.0")
    implementation("androidx.hilt:hilt-work:1.1.0")
    implementation("androidx.hilt:hilt-navigation:1.1.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

        /// ViewModel
            implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
         // ViewModel utilities for Compose
            implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
            implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycle_version")

         // Saved state module for ViewModel
            implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version")

         // Coil
            implementation("io.coil-kt:coil-compose:2.5.0")

            // Retrofit
            implementation ("com.squareup.retrofit2:retrofit:2.9.0")

           // OkHttp
            implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.11")

            // JSON Converter
            implementation("com.squareup.retrofit2:converter-gson:2.9.0")

        //Room
            implementation("androidx.room:room-runtime:$room_version")
            annotationProcessor("androidx.room:room-compiler:$room_version")

         implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
         implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
         implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3")

    // required to avoid crash on Android 12 API 31
        implementation ("androidx.work:work-runtime-ktx:2.7.1")


        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.1.5")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
        androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
        androidTestImplementation("androidx.compose.ui:ui-test-junit4")
        debugImplementation("androidx.compose.ui:ui-tooling")
        debugImplementation("androidx.compose.ui:ui-test-manifest")
}