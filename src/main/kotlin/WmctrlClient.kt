import command.CommandExecutorImpl
import model.CommandModel
import model.WindowModel
import java.io.File

class WmctrlClient() {
    private val commandExecutor by lazy { CommandExecutorImpl() }
    fun getAllWindows(): List<WindowModel> {
        try {
            val process = Runtime.getRuntime().exec("wmctrl -l -x")
            val reader = process.inputStream.bufferedReader()
            val lines = reader.readLines()
            return lines.map { line ->
                val parts = line.split(Regex("\\s+"))
                WindowModel(
                    id = parts[0],
                    workspace = parts[1].toInt(),
                    applicationId = parts[2],
                    title = parts.drop(3).joinToString(" ")
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return emptyList()
        }
    }

    fun moveWindow(windowModel: WindowModel, workspace: Int) {
        try {
            Runtime.getRuntime().exec("wmctrl -i -r ${windowModel.id} -t $workspace")
        } catch (e: Exception) {
            e.printStackTrace()
            println("Error moving window ${windowModel.id} to workspace $workspace")
        }
    }

    fun saveCurrentWindowsState() {
        try {
            val commandModel = CommandModel(
                "wmctrl",
                listOf("-l", "-x", ">", "/home/ubuntu/.wmctrl/wmctrl-state"),
                "/home/ubuntu/"
            )

            val result = commandExecutor.execute(commandModel)

            if (result.error.isEmpty() && result.exitCode == 0) {
                println("Current windows state saved")
            } else {
                println("Error saving current windows state")
                println("Error is ${result.error}")

                if (result.error.contains("No such file or directory")) {
                    createWmctrlDirectory()
                }
            }

            /*val process = Runtime.getRuntime()
                .exec("wmctrl -l -x > /home/ubuntu/.wmctrl/wmctrl-state", null, File("/home/ubuntu/"))
            val error = process.errorStream.bufferedReader().readLines()

            if (error.isEmpty()) {
                println("Current windows state saved")
            } else {
                println("Error saving current windows state")
                println("Error is $error")
                if (error[0].contains("No such file or directory")) {
                    println("Creating .wmctrl directory")
                    Runtime.getRuntime().exec("mkdir .wmctrl")
                    println("Retrying to save current windows state")
                    saveCurrentWindowsState()
                }
            }*/
        } catch (e: Exception) {
            e.printStackTrace()
            println("Error saving current windows state")
        }
    }

    private fun createWmctrlDirectory() {
        val createDirectoryCommand = CommandModel(
            "mkdir",
            listOf(".wmctrl"),
            "/home/ubuntu/"
        )

        val result = commandExecutor.execute(createDirectoryCommand)

        if (result.error.isEmpty() && result.exitCode == 0) {
            println("Created .wmctrl directory")
        } else {
            println("Error creating .wmctrl directory")
            println("Error is ${result.error}")
        }

        saveCurrentWindowsState()
    }
}