import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.compose.compiler)

}

android {
    namespace = "com.timife.githubapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.timife.githubapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        val properties = Properties().apply{
            load(project.rootProject.file("local.properties").inputStream())
        }
        buildConfigField("String","BEARER_TOKEN","\"${properties.getProperty("bearer-token")}\"")

    }
    buildFeatures{
        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.room)
    implementation(libs.roomKtx)
    implementation(libs.hilt)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.retrofit)
    implementation(libs.coroutinesAdapter)
    implementation(libs.coroutineCore)
    implementation(libs.coroutineAndroid)
    implementation(libs.viewModelCompose)
    implementation(libs.moshi)
    ksp(libs.dagger.hilt.compiler)
    ksp(libs.roomCompiler)
    implementation(libs.timber)
    implementation(libs.coroutineCore)
    implementation(libs.coroutineAndroid)
    implementation(libs.coroutinesAdapter)
    implementation(libs.hiltCompose)
    implementation(libs.lifecycle)
    implementation(libs.materialIcons)
    implementation(libs.coil)
    implementation(libs.okHttp)
    implementation(libs.interceptor)
    implementation(libs.moshi.converter)
    implementation(libs.moshi.kotlin)
    implementation(libs.json.serializer)
    implementation(libs.logging.interceptor)
    implementation(libs.chuckerLib)



    testImplementation(libs.junit)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.core.test)
    testImplementation(libs.truth)
    testImplementation(libs.turbine)
    testImplementation(libs.mockk)
    testImplementation(libs.runner)

    androidTestImplementation(libs.truth)
    androidTestImplementation(libs.coroutines.test)
    androidTestImplementation(libs.core.test)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}