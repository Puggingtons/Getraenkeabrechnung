plugins {
    id("java")
    id("org.sonarqube") version "3.5.0.2730"
    id("jacoco")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jacocoTestReport {
    reports {
        // Setting xml.required to true to generate XML report for code coverage
        xml.required.set(true)
    }
}