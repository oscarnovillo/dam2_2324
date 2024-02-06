import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"
    id("com.apollographql.apollo3").version("3.7.5")
    id("org.openjfx.javafxplugin").version("0.0.9")
}

group = "org.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-javafx:1.6.4")

    implementation("org.openjfx:javafx-base:17")
    implementation("org.openjfx:javafx-fxml:17")

    implementation("com.apollographql.apollo3:apollo-api:3.7.5")



    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "21"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}


apollo {
    service("rickMorty") {
        sourceFolder.set("org/example/rickMorty")
        packageName.set("org.example.rickMorty")
    }
    service("server") {
        sourceFolder.set("org/example/server")
        packageName.set("org.example.server")
    }
    service("localhost") {
        sourceFolder.set("org/example/localhost")
        packageName.set("org.example.localhost")
    }
    // instruct the compiler to generate Kotlin models
    generateKotlinModels.set(true)
}

