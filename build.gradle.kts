plugins {
    java
    kotlin("jvm") version "1.5.31"
    id("me.champeau.gradle.jmh") version "0.5.3"
}

group = "com.wrongwrong"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation(group = "com.fasterxml.jackson.core", name = "jackson-databind", version = "2.13.1")

    // use released
    implementation(group = "com.fasterxml.jackson.module", name = "jackson-module-kotlin", version = "2.13.1")
    // use local
    // implementation(files("\${Absolute path to jar}"))
    // implementation(kotlin("reflect"))
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
        exclude("META-INF/versions/11/module-info.class")
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
