import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
    application
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"
    id("com.apollographql.apollo3").version("3.7.5")
    id("org.openjfx.javafxplugin").version("0.0.9")
}

group = "org.example"
version = "0.0.1-SNAPSHOT"

application {
    mainModule.set("frontend")
    mainClass.set("org.example.frontend.MainFX")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
//    modularity.inferModulePath.set(true)
}


repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
//    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
//    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-javafx:1.6.4")
    implementation("com.graphql-java:graphql-java-extended-scalars:21.0")
    implementation("org.openjfx:javafx-base:16")
    implementation("org.openjfx:javafx-fxml:16")

    implementation("com.apollographql.apollo3:apollo-api:3.7.5")
    implementation("com.apollographql.apollo3:apollo-runtime:3.7.5")
    // optional: if you want to use the normalized cache
    implementation("com.apollographql.apollo3:apollo-normalized-cache-sqlite:3.7.5")


    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "21"
    }
}
//tasks.named("compileJava", JavaCompile::class.java) {
//    options.compilerArgumentProviders.add(CommandLineArgumentProvider {
//        // Provide compiled Kotlin classes to javac – needed for Java/Kotlin mixed sources to work
//        listOf("--patch-module", "frontend=${sourceSets["main"].output.asPath}")
//    })
//}
tasks.withType<Test> {
    useJUnitPlatform()
}
javafx {
    version = "16"

    modules = listOf("javafx.controls","javafx.fxml")
}

apollo {
    service("visitas") {
        sourceFolder.set("org/example/visitas")
        packageName.set("org.example.visitas")
    }
    // instruct the compiler to generate Kotlin models
    generateKotlinModels.set(true)
}

