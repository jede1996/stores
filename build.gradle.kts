plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.3.7"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.luna-vet"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}
val springBootStarterValidationVersion = "2.6.3"
val springdocOpenapiStarterWebmvcUiVersion = "2.5.0"
val jsonwebtokenJjwt = "0.9.1"
val jjwt = "0.12.6"

dependencies {
	implementation("io.jsonwebtoken:jjwt-api:$jjwt")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springdocOpenapiStarterWebmvcUiVersion")
	implementation("org.springframework.boot:spring-boot-starter-validation:$springBootStarterValidationVersion")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")

	runtimeOnly("io.jsonwebtoken:jjwt-jackson:$jjwt")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:$jjwt")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
