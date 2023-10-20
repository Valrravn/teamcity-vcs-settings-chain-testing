package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.python
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'BC5'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("BC5")) {
    expectSteps {
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
    steps {
        insert(0) {
            script {
                name = "WriteLine"
                id = "WriteLine"
                scriptContent = """echo "Running BC5 config" > output.txt"""
            }
        }
        items.removeAt(1)
    }
}
