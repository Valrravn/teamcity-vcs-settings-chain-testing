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
        python {
            name = "PyScript"
            id = "PyScript"
            command = script {
                content = """
                    print("Running BC5 configuration...")
                    
                    
                    with open("output.txt", "a+") as openFile:
                        openFile.seek(0)
                        # If file is not empty then append '\n'
                        data = openFile.read(100)
                        if len(data) > 0 :
                            openFile.write("\n")
                        # Append text at the end of file
                        openFile.write("Running BC5 configuration (main branch)...")
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
        python {
            name = "PyScript"
            id = "PyScript"
            command = script {
                content = """
                    print("Running BC4 configuration...")
                    
                    
                    with open("output.txt", "a+") as openFile:
                        openFile.seek(0)
                        # If file is not empty then append '\n'
                        data = openFile.read(100)
                        if len(data) > 0 :
                            openFile.write("\n")
                        # Append text at the end of file
                        openFile.write("Running BC4 configuration (custom branch)...")
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
        python {
            name = "PyScript"
            id = "PyScript"
            command = script {
                content = """
                    print("Running BC3 configuration...")
                    
                    
                    with open("output.txt", "a+") as openFile:
                        openFile.seek(0)
                        # If file is not empty then append '\n'
                        data = openFile.read(100)
                        if len(data) > 0 :
                            openFile.write("\n")
                        # Append text at the end of file
                        openFile.write("Running BC3 configuration (main branch)...")
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
                        openFile.write("Running BC2 configuration (custom branch)...")
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
        python {
            name = "PyScript"
            id = "PyScript"
            command = script {
                content = """
                    print("Running BC0 configuration...")
                    
                    
                    with open("output.txt", "a+") as openFile:
                        openFile.seek(0)
                        # If file is not empty then append '\n'
                        data = openFile.read(100)
                        if len(data) > 0 :
                            openFile.write("\n")
                        # Append text at the end of file
                        openFile.write("Running BC0 configuration (main branch)...")
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
