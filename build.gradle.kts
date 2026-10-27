plugins {
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.quarkus)
  id("io.github.bstdoom.site-content")
}

group = "io.github.bstdoom"
version = "0.1.0-SNAPSHOT"

val javaVersion = rootProject.file(".java-version").readText().trim().substringBefore('.').toInt()

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(javaVersion)
  }
}

quarkus {
  if (gradle.startParameter.taskNames.any {
      it == "roqGenerate" || it.endsWith(":roqGenerate") ||
        it == "roqServe" || it.endsWith(":roqServe")
    }) {
    quarkusBuildProperties.put("quarkus.roq.generator.batch", "true")
  }
}

repositories {
  mavenCentral()
}

val hostOperatingSystem = when {
  System.getProperty("os.name").lowercase().contains("mac") -> OperatingSystemFamily.MACOS
  System.getProperty("os.name").lowercase().contains("win") -> OperatingSystemFamily.WINDOWS
  else -> OperatingSystemFamily.LINUX
}

val hostArchitecture = when (System.getProperty("os.arch").lowercase()) {
  "aarch64", "arm64" -> MachineArchitecture.ARM64
  else -> MachineArchitecture.X86_64
}

configurations.configureEach {
  if (isCanBeResolved) {
    attributes {
      attribute(
        OperatingSystemFamily.OPERATING_SYSTEM_ATTRIBUTE,
        objects.named(hostOperatingSystem),
      )
      attribute(
        MachineArchitecture.ARCHITECTURE_ATTRIBUTE,
        objects.named(hostArchitecture),
      )
    }
  }
}

dependencies {
  implementation(enforcedPlatform(libs.quarkus.bom))

  implementation(libs.quarkus.config.yaml)
  implementation(libs.quarkus.kotlin)
  implementation(libs.quarkus.roq)
  implementation(libs.quarkus.vertx)
}

tasks.named<Test>("test") {
  useJUnitPlatform()
}

// Quarkus's Gradle code-generation task checks this conventional Java output
// directory even though this project intentionally contains Kotlin sources only.
val prepareQuarkusJavaOutput = tasks.register("prepareQuarkusJavaOutput") {
  val javaOutputDirectory = layout.buildDirectory.dir("classes/java/main")
  outputs.dir(javaOutputDirectory)
  doLast {
    javaOutputDirectory.get().asFile.mkdirs()
  }
}

tasks.matching { it.name.startsWith("quarkusGenerateCode") }.configureEach {
  dependsOn(prepareQuarkusJavaOutput)
}

val roqCli = providers.gradleProperty("roq.cli")
  .orElse("${System.getProperty("user.home")}/.jbang/bin/roq")
  .get()

tasks.register<Exec>("roqGenerate") {
  group = "roq"
  description = "Generate the Roq site as static files under build/roq."
  dependsOn(tasks.named("quarkusBuild"))

  workingDir(project.projectDir)
  val roqJar = layout.buildDirectory.file("quarkus-app/quarkus-run.jar").get().asFile.absolutePath
  commandLine("java", "-jar", roqJar)
  // Keep internal generation isolated from the long-running dev server on 8082.
  environment("QUARKUS_HTTP_PORT", "18083")
}

tasks.register<Exec>("roqServe") {
  group = "roq"
  description = "Generate the Roq site and serve the generated static files."
  dependsOn(tasks.named("roqGenerate"))

  workingDir(project.projectDir)
  commandLine(roqCli, "serve", layout.buildDirectory.dir("roq").get().asFile.absolutePath)
}
