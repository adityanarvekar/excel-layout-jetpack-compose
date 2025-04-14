plugins {
    id("com.android.library")
    id("maven-publish")
    alias(libs.plugins.jetbrains.kotlin.android)
}
group = "com.github.adityanarvekar"
version = "1.0.2"
android {
    namespace = "com.adityanarvekar.stickymatrix"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        targetSdk = 34
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    publishing {
        multipleVariants {
            allVariants() // Make both debug and release variants consumable
        }
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.material3)
    implementation (libs.material)
    implementation(libs.androidx.ui)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.github.adityanarvekar"
                artifactId = "sticky-matrix"
                version = "1.0.2"
            }
        }
    }
}