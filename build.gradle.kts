plugins {
    kotlin("jvm") version "2.0.0"
    java
    id("org.graalvm.buildtools.native") version "0.10.1"
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
    from({
        configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }
    })

    duplicatesStrategy = DuplicatesStrategy.INCLUDE
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

graalvmNative {
    binaries {
        named("main") {
            mainClass.set("xd.arkosammy.edtr.Edtr")
        }
    }
}