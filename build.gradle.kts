plugins {
    java
    kotlin("jvm") version "1.3.72"
    id("org.jetbrains.kotlin.plugin.noarg") version "1.3.72"
    id("me.champeau.gradle.jmh") version "0.5.2"
}

group = "com.wrongwrong"
version = "0.1.0"

repositories {
    mavenCentral()
}

noArg {
    annotation("com.wrongwrong.NoArg")
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation(group = "com.fasterxml.jackson.core", name = "jackson-databind", version = "2.12.0")
    implementation(group = "com.fasterxml.jackson.module", name = "jackson-module-kotlin", version = "2.12.0")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }
    // https://qiita.com/wrongwrong/items/16fa10a7f78a31830ed8
    jmhJar {
        exclude("META-INF/versions/9/module-info.class")
    }
}

jmh {
    fork = 3
    iterations = 3
    warmupBatchSize = 3
    warmupIterations = 3

    failOnError = true
    isIncludeTests = false

    resultFormat = "CSV"
}
