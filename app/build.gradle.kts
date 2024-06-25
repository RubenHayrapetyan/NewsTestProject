plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.jetbrains.kotlin.android)
  alias(libs.plugins.compose.compiler)
  alias(libs.plugins.hilt.android)
  id ("kotlin-kapt")
}

android {
  namespace = "com.my.newsarticles"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.my.newsarticles"
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
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.1"
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {
  implementation(project(":data"))
  implementation(project(":domain"))
  implementation(project(":presentation"))

  implementation (libs.jsoup)
  implementation (libs.androidx.navigation.compose)
  implementation(libs.room.runtime)
  implementation (libs.room.paging)
  implementation (libs.androidx.paging.runtime.ktx)
  implementation (libs.androidx.paging.compose)
  implementation (libs.retrofit)
  implementation (libs.retrofit2.converter.moshi)
  implementation (libs.logging.interceptor)
  implementation(libs.androidx.lifecycle.runtime.ktx.v270)
  implementation(libs.androidx.activity.compose.v182)
  implementation(libs.hilt.android)
  kapt (libs.hilt.compiler)
  implementation (libs.hilt.navigation.compose)
  implementation(libs.coil.compose)
  implementation(libs.androidx.core.ktx)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.ui.tooling.preview)
  implementation(libs.androidx.material3)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.ui.test.junit4)
  debugImplementation(libs.androidx.ui.tooling)
  debugImplementation(libs.androidx.ui.test.manifest)
}
kapt {
  correctErrorTypes = true
}