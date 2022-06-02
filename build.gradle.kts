import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.0"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	id("com.netflix.dgs.codegen") version "5.1.17"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	implementation("io.micrometer:micrometer-registry-prometheus:1.9.0")
	implementation("org.springframework.boot:spring-boot-starter-actuator")

	// implementation(platform("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:4.10.2"))
	// implementation(platform("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:4.10.3"))
	implementation(platform("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:4.10.4"))
	// implementation(platform("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:5.0.0"))
	// implementation(platform("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:5.0.1"))
	implementation("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter")
	implementation("com.netflix.graphql.dgs:graphql-dgs-spring-boot-micrometer")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<com.netflix.graphql.dgs.codegen.gradle.GenerateJavaTask> {
	schemaPaths = mutableListOf("${projectDir}/src/main/resources/schema")
	packageName = "com.example.demo"
	generateClient = false
}
