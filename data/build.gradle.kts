plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.jetbrains.kotlin.android)
  alias(libs.plugins.hilt.android)
  id ("kotlin-kapt")
}

android {
  namespace = "com.my.data"
  compileSdk = 34

  defaultConfig {
    minSdk = 24

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")
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
  kotlinOptions {
    jvmTarget = "1.8"
  }
}

dependencies {
  implementation(project(":domain"))

  kapt (libs.hilt.compiler)
  implementation(libs.hilt.android)
  implementation (libs.room.paging)
  implementation (libs.room.runtime)
  kapt (libs.androidx.room.compiler)
  implementation (libs.androidx.room.ktx)
  implementation (libs.androidx.paging.runtime)
  implementation (libs.androidx.paging.compose)
  implementation (libs.retrofit)
  implementation (libs.logging.interceptor)
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.material)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
}
kapt {
  correctErrorTypes = true
}