plugins {
	java
	id("org.springframework.boot") version "3.2.2"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "dev.cstraka"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-graphql")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.session:spring-session-core")
  implementation("io.opentelemetry:opentelemetry-api")
	implementation("org.postgresql:postgresql:42.7.1")
  implementation(platform("io.opentelemetry:opentelemetry-bom:1.34.1"))
  developmentOnly("org.springframework.boot:spring-boot-docker-compose")
	testAndDevelopmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework:spring-webflux")
	testImplementation("org.springframework.graphql:spring-graphql-test")
	testImplementation("org.springframework.security:spring-security-test")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.springframework.boot:spring-boot-testcontainers")
  testImplementation("org.testcontainers:junit-jupiter")
  testImplementation("org.testcontainers:postgresql")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.register<Exec>("dev") {
	group = "Application"
	description = "Runs the project using docker for manual testing"
	commandLine("docker-compose", "up")
}

tasks.register<Exec>("tdd") {
	group = "Application"
	description = "Runs the project with continuous testing"
	commandLine("./gradlew", "test", "--continuous")
}
