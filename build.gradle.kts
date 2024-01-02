plugins {
	java
	id("org.springframework.boot") version "3.1.5"
	id("io.spring.dependency-management") version "1.1.3"
}

group = "com.laundry"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web"){
		exclude(module= "spring-boot-starter-logging")
	}
	implementation("org.springframework.boot:spring-boot-starter-data-jpa"){
		exclude(module= "spring-boot-starter-logging")
	}
	implementation("com.h2database:h2")
	developmentOnly("org.springframework.boot:spring-boot-devtools"){
		exclude(module= "spring-boot-starter-logging")
	}
	testImplementation("org.springframework.boot:spring-boot-starter-test"){
		exclude(module= "spring-boot-starter-logging")
	}
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
	implementation("org.springframework.boot:spring-boot-starter-security"){
		exclude(module= "spring-boot-starter-logging")
	}
	testImplementation("org.springframework.security:spring-security-test")
	implementation("org.apache.logging.log4j:log4j-api:2.22.1")
	implementation("org.apache.logging.log4j:log4j-core:2.22.1")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.bootBuildImage {
	builder.set("paketobuildpacks/builder-jammy-base:latest")
}

