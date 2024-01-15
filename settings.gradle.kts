pluginManagement {
    repositories {
        maven {
            url =
                uri("https://inexus.samentic.com/repository/samentic-android")
            artifactUrls("https://inexus.samentic.com/repository/samentic-android")
            credentials {
                username = "mozhdeh.nouri"
                password = "Av48,HZh%q4=S'2"
            }
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven {
            url =
                uri("https://inexus.samentic.com/repository/samentic-android")
            artifactUrls("https://inexus.samentic.com/repository/samentic-android")
            credentials {
                username = "mozhdeh.nouri"
                password = "Av48,HZh%q4=S'2"
            }
        }
    }
}

rootProject.name = "TodoAppWithCleanArchitecture"
include(":app")
 