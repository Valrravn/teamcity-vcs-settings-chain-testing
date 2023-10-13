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

    buildType(Bc2)
    buildType(DefaultBranchBuild)
}

object Bc2 : BuildType({
    name = "BC2"

    artifactRules = "output.txt"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        python {
            name = "PyScript"
            id = "PyScript"
            command = script {
                content = """
                    print("Running BC2 configuration...")
                    
                    
                    with open("output.txt", "a+") as openFile:
                        openFile.seek(0)
                        # If file is not empty then append '\n'
                        data = openFile.read(100)
                        if len(data) > 0 :
                            openFile.write("\n")
                        # Append text at the end of file
                        openFile.write("Running BC2 configuration...")
                """.trimIndent()
            }
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
        dependency(DefaultBranchBuild) {
            snapshot {
                reuseBuilds = ReuseBuilds.NO
            }

            artifacts {
                artifactRules = "output.txt"
            }
        }
    }
})

object DefaultBranchBuild : BuildType({
    name = "BC1"

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
        python {
            name = "PyScript"
            id = "PyScript"
            command = script {
                content = """
                    print("Running BC1 configuration...")
                    
                    
                    with open("output.txt", "a+") as openFile:
                        openFile.seek(0)
                        # If file is not empty then append '\n'
                        data = openFile.read(100)
                        if len(data) > 0 :
                            openFile.write("\n")
                        # Append text at the end of file
                        openFile.write("Running BC1 configuration...")
                """.trimIndent()
            }
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
