import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    //fcm
    id("com.google.gms.google-services")
}
//gitignore
val properties = Properties().apply { load(FileInputStream(File(rootProject.rootDir, "local.properties")))}

android {
    namespace = "com.example.ezorder"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.ezorder"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        //gitignore
        buildConfigField("String", "NAVER_MAP_ID", properties.getProperty("NAVER_MAP_ID"))
        buildConfigField("String", "BASE_URL", properties.getProperty("BASE_URL"))
        buildConfigField("String", "SERVER_KEY", properties.getProperty("SERVER_KEY"))
        buildConfigField("String", "CONTENT_TYPE", properties.getProperty("CONTENT_TYPE"))
        resValue("string","NAVER_MAP_ID",properties.getProperty("NAVER_MAP_ID"))

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment:2.5.3")
    implementation("androidx.navigation:navigation-ui:2.5.3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    // 네이버 지도 SDK
    implementation("com.naver.maps:map-sdk:3.17.0")
    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    //glide(그림)
    implementation("com.github.bumptech.glide:glide:4.11.0")
    //fcm
    implementation(platform("com.google.firebase:firebase-bom:32.2.2"))
    implementation("com.google.firebase:firebase-analytics-ktx")
    implementation("com.google.firebase:firebase-messaging")
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-installations-ktx:17.1.4")
    //google worker
    implementation("androidx.work:work-runtime:2.8.1")
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
}
