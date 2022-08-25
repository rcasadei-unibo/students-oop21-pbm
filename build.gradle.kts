plugins {
    // Apply the java plugin to add support for Java
    java

    // Apply the application plugin to add support for building a CLI application
    // You can run your app via task "run": ./gradlew run
    application

    /*
     * Adds tasks to export a runnable jar.
     * In order to create it, launch the "shadowJar" task.
     * The runnable jar will be found in build/libs/projectname-all.jar
     */
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

repositories {
    mavenCentral()
}

val javaFXModules = listOf(
    "base",
    "controls",
    "fxml",
    "swing",
    "graphics"
)

val supportedPlatforms = listOf("linux", "mac", "win") // All required for OOP
val jUnitVersion = "5.7.1"
val javaFxVersion = 15

dependencies {
    // Example library: Guava. Add what you need (and remove Guava if you don't use it)
    implementation("com.google.guava:guava:30.1.1-jre")

    // JavaFX: comment out if you do not need them
    for (platform in supportedPlatforms) {
        for (module in javaFXModules) {
            implementation("org.openjfx:javafx-$module:$javaFxVersion:$platform")
        }
    }

    // JUnit API and testing engine
    testImplementation("org.junit.jupiter:junit-jupiter-api:$jUnitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$jUnitVersion")
    
    // https://mvnrepository.com/artifact/com.yahoofinance-api/YahooFinanceAPI
    implementation("com.yahoofinance-api:YahooFinanceAPI:3.17.0")
    
    // https://mvnrepository.com/artifact/org.json/json
    implementation("org.json:json:20220320")    
    //implementation("org.apache.logging.log4j:log4j-api:2.14.1")
    //runtimeOnly("org.apache.logging.log4j:log4j-core:2.14.1")
    
    // https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple
    implementation("com.googlecode.json-simple:json-simple:1.1.1")
    implementation("com.github.cliftonlabs:json-simple:4.0.0")

    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.3")
    
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Test> {
    // Enables JUnit 5 Jupiter module
    useJUnitPlatform()
}

application {
    // Define the main class for the application
    mainClass.set("main.application.App")
}
