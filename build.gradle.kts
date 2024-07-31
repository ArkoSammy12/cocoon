plugins {
    kotlin("jvm") version "2.0.0"
    java
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = property("maven_group")!!
version = property("project_version")!!
val archiveName: String = property("archive_base_name").toString()

repositories {
    mavenCentral()
}

tasks.withType<Jar> {
    manifest {
        attributes["Manifest-Version"] = version
        attributes["Main-Class"] = "xd.arkosammy.edtr.Edtr"
    }
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.googlecode.lanterna:lanterna:${property("lanterna_version")}")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

tasks.shadowJar {
    archiveBaseName.set(archiveName)
    archiveClassifier.set("")
    manifest {
        attributes["Manifest-Version"] = version
        attributes["Main-Class"] = "xd.arkosammy.edtr.Edtr"
    }
}
