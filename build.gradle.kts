plugins {
	java
	id("org.springframework.boot") version "3.1.5"
	id("io.spring.dependency-management") version "1.1.3"
	checkstyle
	id("org.kordamp.gradle.errorprone") version "0.54.0"
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
	implementation("org.apache.httpcomponents:httpclient:4.5")
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
	//errorprone("com.google.errorprone:error_prone_core:2.3.3")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.register("generateJavadoc", Javadoc::class) {
	source = sourceSets["main"].allJava
	classpath = configurations["runtimeClasspath"]
	destinationDir = file("build/docs/javadoc")
	(options as StandardJavadocDocletOptions).apply {
		setMemberLevel(JavadocMemberLevel.PUBLIC)
		isAuthor = true
		isVersion = true
	}
}

tasks.named<org.springframework.boot.gradle.tasks.bundling.BootBuildImage>("bootBuildImage") {
	builder = "paketobuildpacks/builder-jammy-base:latest"
}

tasks.bootBuildImage {
	builder.set("paketobuildpacks/builder-jammy-base:latest")
}