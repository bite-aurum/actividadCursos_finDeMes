plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.cursos"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.cursos"
        minSdk = 28
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = true
                packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    // Kotlin Serialization JSON
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")

    // Convertidor de Kotlin Serialization para Retrofit
    implementation(
        "com.squareup.retrofit2:converter-kotlinx-serialization:2.11.0"
    )

    // OkHttp
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    // Glide para imágenes
    implementation("com.github.bumptech.glide:glide:4.16.0")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}