Why we need this two file:

1. `init.gradle.kts`: This is a Kotlin script for setting up a code formatting tool called Spotless
   in your Gradle build. Spotless helps keep your code looking neat and consistent. The script does
   the following:

   - Defines the version of`ktlint`, a Kotlin linter, which is a tool that ensures your code adheres
     to style guidelines.
   - Sets up Spotless as a dependency in the build system.
   - Applies the Spotless plugin to all projects except the root project.
   - Configures Spotless to go through Kotlin files (`*.kt`) and Kotlin script files (`*.kts`),
     excluding any in the build folders.
   - Sets up`ktlint`with some rules override like the Android coding style, enables trailing commas,
     and temporarily disables a set of new rules introduced in`ktlint 0.46.0`that require further
     discussion.
   - Specifies a copyright header that should be included at the top of Kotlin files for licensing
     information.

2. `toml-updater-config.gradle`: This is a Groovy script used to configure the automation of Gradle
   version catalog updates. The version catalog is a list of dependencies and plugins with their
   respective versions, making it easier to manage and update them. The script does the following:

   - Configures the updater to sort entries by key for easier readability.
   - Sets the updater to keep track of unused versions, libraries, and plugins, even if they’re not
     currently used in the project. This could be helpful for documentation or other reasons.
   - Defines a closure (`isNonStable`) that checks if a version string corresponds to a non-stable (
     e.g., alpha, beta, release candidate) release.
   - Configures the`dependencyUpdates`task to use custom logic to determine if a dependency update
     should be accepted or rejected based

why we need to write that code into build.gradle.kts :

he`toml-updater-config.gradle`script does not automatically update dependencies. Instead, it
configures the`dependencyUpdates`task to determine which dependencies have updates available.

The`init.gradle.kts`is an initialization script written in Kotlin Script (hence the`.kts`extension).
It is used to initialize a Gradle build environment before the project build scripts are executed.
This script is applied across all the projects in the build, with some particular behaviors and
configurations set up before the actual build process begins.

Here’s why and how`init.gradle.kts`differs from the root`build.gradle`:

1. **Scope**:

   - **`init.gradle.kts`**: It’s run very early in the build process for every build that occurs
     with that Gradle installation. It is typically used for cross-cutting concerns that are common
     to all projects, such as configuring a shared repository or setting up enterprise-wide Gradle
     settings.
   - **Root`build.gradle`**: This is the build script for the root project. It is for configuring
     the specific build for that project or its subprojects.

2. **Applied to**:

   - **`init.gradle.kts`**: It affects all Gradle projects built with that particular Gradle
     installation if placed in the`GRADLE_USER_HOME`directory or affects only the current build if
     passed as an initialization script argument at the command line. Typically, it won’t apply
     changes to the build script of the root project directly. Instead, it could modify the build
     environment or setup plugins globally.
   - **Root`build.gradle`**: It configures the root project and its subprojects only. It is part of
     the project’s codebase and can be checked into source control.

In the`init.gradle.kts`, there’s a specific check

```kt
allprojects {
   if (this == rootProject) {
      return@allprojects
   }
   // Configurations to avoid applying to the root project
}
```

This check (`if (this == rootProject)`) essentially means “if the currently evaluated project is the
root project, do not apply the following configurations”. The script applies the Spotless plugin to
all subprojects but intentionally skips the root project. There could be several reasons for this
behavior:

- The root project might not contain any source code; it might only be used for aggregating
  subprojects.
- The root project might have its own specific formatting rules, or perhaps it manages its source
  formatting differently.
- To avoid conflicts or redundancies if the root project already has a different code formatting
  setup.
