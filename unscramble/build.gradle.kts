plugins {
  jacoco
  id("com.android.application")
  id("org.jetbrains.kotlin.plugin.compose")
}

android {
  namespace = "com.example.unscramble"
  compileSdk = 37

  defaultConfig {
    applicationId = "com.example.unscramble"
    minSdk = 24
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildFeatures {
    compose = true
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
}

dependencies {
  val composeBom = platform("androidx.compose:compose-bom:2025.08.00")
  implementation(composeBom)
  implementation("androidx.activity:activity-compose:1.10.1")
  implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
  implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.7")
  implementation("androidx.compose.material3:material3")
  implementation("androidx.compose.ui:ui-tooling-preview")
  debugImplementation("androidx.compose.ui:ui-tooling")
  testImplementation("junit:junit:4.13.2")
  androidTestImplementation(composeBom)
  androidTestImplementation("androidx.compose.ui:ui-test-junit4")
  androidTestImplementation("androidx.test:runner:1.6.2")
  debugImplementation("androidx.compose.ui:ui-test-manifest")
}


jacoco {
  toolVersion = "0.8.13"
}

tasks.register<JacocoReport>("jacocoTestReport") {
  dependsOn("testDebugUnitTest")
  reports {
    xml.required.set(true)
    html.required.set(true)
  }
  val classes = fileTree(layout.buildDirectory.dir("intermediates/built_in_kotlinc/debug/compileDebugKotlin/classes")) {
    include("com/example/unscramble/ui/GameViewModel*.class")
  }
  classDirectories.setFrom(classes)
  sourceDirectories.setFrom(files("src/main/java"))
  executionData.setFrom(layout.buildDirectory.file("outputs/unit_test_code_coverage/debugUnitTest/testDebugUnitTest.exec"))
}
