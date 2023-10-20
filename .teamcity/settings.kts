import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.python
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.triggers.vcs

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2023.05"

project {
    buildType(BC5)
    buildType(BC4)
    buildType(BC3)
    buildType(BC2)
    buildType(BC0)
}

// 5th build config
object BC5 : BuildType({
    name = "BC5"

    artifactRules = "output.txt"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
                name = "WriteLine"
                id = "WriteLine5"
                scriptContent = """echo "Running BC5 config" >> output.txt"""
        }
    }

    triggers {
        vcs {
        }
    }

    features {
        perfmon {
        }
    }

    dependencies {
        dependency(BC4) {
            snapshot {
                reuseBuilds = ReuseBuilds.NO
            }

            artifacts {
                artifactRules = "output.txt"
            }
        }
    }
})

// 4th build config
object BC4 : BuildType({
    name = "BC4"

    artifactRules = "output.txt"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
                name = "WriteLine"
                id = "WriteLine4"
                scriptContent = """echo "Running virtual BC4 config" >> output.txt"""
        }
    }

    triggers {
        vcs {
        }
    }

    features {
        perfmon {
        }
    }

    dependencies {
        dependency(BC3) {
            snapshot {
                reuseBuilds = ReuseBuilds.NO
            }

            artifacts {
                artifactRules = "output.txt"
            }
        }
    }
})


// 3rd build config
object BC3 : BuildType({
    name = "BC3"

    artifactRules = "output.txt"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
                name = "WriteLine"
                id = "WriteLine3"
                scriptContent = """echo "Running BC3 config" >> output.txt"""
        }
    }

    triggers {
        vcs {
        }
    }

    features {
        perfmon {
        }
    }

    dependencies {
        dependency(BC2) {
            snapshot {
                reuseBuilds = ReuseBuilds.NO
            }

            artifacts {
                artifactRules = "output.txt"
            }
        }
    }
})

// 2nd build config
object BC2 : BuildType({
    name = "BC2"

    artifactRules = "output.txt"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
                name = "WriteLine"
                id = "WriteLine2"
                scriptContent = """echo "Running virtual BC2 config" >> output.txt"""
        }
    }

    triggers {
        vcs {
        }
    }

    features {
        perfmon {
        }
    }

    dependencies {
        dependency(BC0) {
            snapshot {
                reuseBuilds = ReuseBuilds.NO
            }

            artifacts {
                artifactRules = "output.txt"
            }
        }
    }
})

// 1st build config
object BC0 : BuildType({
    name = "BC0"

    artifactRules = "output.txt"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            name = "Prepare File"
            id = "Prepare_File"
            scriptContent = """
                rm output.txt
                touch output.txt
            """.trimIndent()
        }
        script {
                name = "WriteLine"
                id = "WriteLine0"
                scriptContent = """echo "Running BC5 config" > output.txt"""
        }
    }

    triggers {
        vcs {
        }
    }

    features {
        perfmon {
        }
    }
})
