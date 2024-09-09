plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.services)
    alias(libs.plugins.ksp)
}

android {
    namespace = "br.com.geocdias.radiolacompose"
    compileSdk = 34

    defaultConfig {
        applicationId = "br.com.geocdias.radiolacompose"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)

    // Load Images
    implementation(libs.coil.compose)

    //Dependency Injection
    implementation(libs.koin)

    // Material 3
    implementation(libs.androidx.material3)

    //Navigation
    implementation (libs.androidx.navigation.compose)

    //Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.storage)
    implementation(libs.firebase.firestore)

    //Exoplayer
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.common)
    implementation(libs.androidx.media3.ui)
    implementation(libs.androidx.media3.session)

    //Room
    implementation(libs.room)
    annotationProcessor(libs.room.compiler)
    implementation(libs.room.coroutine.suport)
    ksp(libs.room.compiler)

    //Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}
