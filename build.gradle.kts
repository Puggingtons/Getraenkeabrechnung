plugins {
    id("java")
    id("jacoco")
    id("org.sonarqube") version "6.0.1.5171"
}

sonar {
  properties {
    property("sonar.projectKey", "Puggingtons_Getraenkeabrechnung")
    property("sonar.organization", "puggingtons")
    property("sonar.host.url", "https://sonarcloud.io")
  }
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.12.1")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

// Set Java compatibility to Java 23
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(23)) // For compatibility with JaCoCo
    }
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
    useJUnitPlatform()
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
    reports {
        xml.required.set(true)
    }
}