plugins {
  id("java")
  id("org.jetbrains.kotlin.jvm") version "1.8.21"
  id("org.jetbrains.intellij") version "1.13.3"
}

group = "cloud.yiyefu"
version = "1.0-SNAPSHOT"

repositories {
  mavenLocal()

  mavenCentral()
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
  version.set("2022.2.5")
  type.set("IC") // Target IDE Platform

  plugins.set(listOf(/* Plugin Dependencies */))
}

tasks {
  // Set the JVM compatibility versions
  withType<JavaCompile> {
    sourceCompatibility = "17"
    targetCompatibility = "17"
  }
  withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
  }

  patchPluginXml {
    sinceBuild.set("222")
    untilBuild.set("232.*")
  }

  signPlugin {
    certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
    privateKey.set(System.getenv("PRIVATE_KEY"))
    password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
  }

  publishPlugin {
    token.set(System.getenv("PUBLISH_TOKEN"))
  }
}
dependencies {
  //implementation group: 'mysql', name: 'mysql-connector-java', version: '8.0.33'
// https://mvnrepository.com/artifact/mysql/mysql-connector-java
  implementation("mysql:mysql-connector-java:8.0.33")
// https://mvnrepository.com/artifact/org.freemarker/freemarker
  implementation("org.freemarker:freemarker:2.3.32")
  // https://mvnrepository.com/artifact/cn.hutool/hutool-all
  implementation("cn.hutool:hutool-all:5.8.18")


}