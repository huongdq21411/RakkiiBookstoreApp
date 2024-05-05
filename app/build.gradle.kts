plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    // Other plugins...
}
android {
    namespace = "com.group8.rakkiibookstoreapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.group8.rakkiibookstoreapp"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    }

}

dependencies {
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.database)
    implementation(platform("com.google.firebase:firebase-bom:32.8.1"))
    implementation("com.google.firebase:firebase-database")
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation ("androidx.core:core-splashscreen:1.0.1")
    implementation ("com.google.zxing:core:3.5.3")
    implementation ("com.journeyapps:zxing-android-embedded:4.3.0")
    implementation ("com.github.TutorialsAndroid:GButton:v1.0.19")
    implementation ("com.google.code.gson:gson:2.10.1")
    implementation ("com.google.android.gms:play-services-vision:20.1.3")
    implementation ("com.google.firebase:firebase-auth:18.0.0")
    implementation ("com.google.android.gms:play-services-gcm:17.0.0")
    implementation ("com.google.android.gms:play-services-auth:21.1.1")


}