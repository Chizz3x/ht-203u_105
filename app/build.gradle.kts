plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.hti.xtherm.hti160203u"
    compileSdk {
        version = release(36)
    }

//    packagingOptions {
//        pickFirst("com/sun/jna/android-aarch64/libjnidispatch.so")
//    }

    defaultConfig {
        applicationId = "com.hti.xtherm.hti160203u"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    sourceSets {
        getByName("main") {
            kotlin.srcDir("src/main/jniLibs")
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    implementation("androidx.startup:startup-runtime:1.1.1")
    implementation("com.github.kongzue.DialogX:DialogX:0.0.48")
    implementation("androidx.appcompat:appcompat:1.6.1")
//    implementation(files("libs/android-aarch64.jar"))
//    implementation(files("libs/jna.jar"))
    implementation("net.java.dev.jna:jna:4.5.0@aar") // 5.13.0 // 5.0.0
//    implementation("net.java.dev.jna:jna-jpms:5.11.0")
//    implementation("net.java.dev.jna:jna-platform-jpms:5.11.0")
//    implementation("com.github.oshi:oshi-core-java11:6.1.6")
    implementation("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")
    implementation(project(":opencv"))
    implementation("pub.devrel:easypermissions:3.0.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("org.jetbrains.kotlin:kotlin-reflect:2.0.21")
    implementation("com.microsoft.onnxruntime:onnxruntime-android:1.17.0")
//    implementation("com.github.QuickBirdEng:opencv-android:4.5.1")
}