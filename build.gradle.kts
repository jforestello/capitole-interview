import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	jacoco
	id("org.springframework.boot") version "3.0.0-M1"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.0"
	kotlin("plugin.spring") version "1.6.0"
}

group = "org.jforestello"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.2")
	testImplementation("io.mockk:mockk:1.12.3")
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

tasks.withType<JacocoReport> {
	reports {
		xml.required.set(true)
		html.required.set(true)
	}
	afterEvaluate {
		val coverageParticipants = classDirectories.files
			.map {
				fileTree(it).exclude(
					"org/jforestello/mytheresa_interview/Application**",
					"org/jforestello/mytheresa_interview/controller/PingController**",
					"org/jforestello/mytheresa_interview/usecase/bootstrap**",
					"org/jforestello/mytheresa_interview/infrastructure/bootstrap**",
					"org/jforestello/mytheresa_interview/infrastructure/model**",
					"org/jforestello/mytheresa_interview/infrastructure/**/model/**",
				)
			}
			.let { files(it) }
		classDirectories.setFrom(coverageParticipants)
	}
}
