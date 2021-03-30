import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
    `java-library`
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

repositories {
    mavenCentral()
    jcenter()
}

version = "0.1"

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}


tasks.test {
    useJUnitPlatform()
    testLogging {
        events = setOf(
            FAILED,
            PASSED,
            SKIPPED
        )
    }
}
