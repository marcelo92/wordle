plugins {
	id("org.springframework.boot") version "2.6.7"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("org.unbroken-dome.test-sets") version "4.0.0"
	java
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

repositories {
	mavenCentral()
}

testSets {
	create("integrationTest")
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.zalando:logbook-spring-boot-starter:2.14.0")
	testImplementation("io.cucumber:cucumber-java:7.3.3")
	testImplementation("io.cucumber:cucumber-junit:7.3.3")
	testImplementation("io.cucumber:cucumber-spring:7.3.3")
	testImplementation("org.springframework.boot:spring-boot-starter-webflux")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

tasks.test {
	useJUnitPlatform()
}

task<Exec>("buildFront") {
	commandLine("npm","run", "build", "--prefix","frontend-react/")
	copy {
		from("frontend-react/build")
		into("src/main/webapp")
	}
}