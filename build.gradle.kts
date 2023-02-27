val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    application
    kotlin("jvm") version "1.8.10"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10"
    id("io.ktor.plugin") version "2.2.3"
}

group = "tech.mfti"
version = "0.0.1"
application {
    mainClass.set("tech.mfti.ApplicationKt")
}

repositories {
    mavenCentral()
}

ktor {
    fatJar {
        archiveFileName.set("fat.jar")
    }
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}

tasks.create("stage") {
    dependsOn("installDist")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "tech.mfti.ApplicationKt"
    }
}