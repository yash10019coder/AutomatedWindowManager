import common.CommandExecutorImpl
import common.FileUtils
import model.CommandModel
import model.WindowModel
import java.util.*

const val workingDirectory = "/home/ubuntu/.wmctrl"

class WmctrlClient {
    private val commandExecutor by lazy { CommandExecutorImpl() }
    private val fileUtils by lazy { FileUtils() }

    fun getAllWindows(): List<WindowModel> {
        return try {
            val lines = getAllWindowsRaw().rawWindowStateToWindowModel()
            lines
        } catch (e: Exception) {
            e.printStackTrace()
            println("Error getting all windows")
            emptyList()
        }
    }

    fun String.rawWindowStateToWindowModel(): List<WindowModel> {
        val rawWindowState = this
        val lines = rawWindowState.split("\n")
        return lines.map { line ->
            val parts = line.split(Regex("\\s+"))
            WindowModel(
                id = parts[0],
                workspace = parts[1].toInt(),
                applicationId = parts[2],
                title = parts.drop(3).joinToString(" ")
            )
        }.sortedBy { it.workspace }
    }

    // TOdo: use command executor
    fun getAllWindowsRaw(): String {
        return try {
            val process = Runtime.getRuntime().exec("wmctrl -l -x")
            val reader = process.inputStream.bufferedReader()
            val lines = reader.readLines()
            lines.joinToString("\n")
        } catch (e: Exception) {
            e.printStackTrace()
            println("Error getting all windows raw")
            ""
        }
    }

    // TOdo: use command executor
    fun moveWindow(windowModel: WindowModel, workspace: Int) {
        try {
            if (windowModel.workspace >= 0) {
                Runtime.getRuntime().exec("wmctrl -i -r ${windowModel.id} -t $workspace")
                println("Moved window ${windowModel.id} to workspace $workspace")
            } else {
                println("Invalid workspace number ${windowModel.workspace}")
                println("Window ${windowModel.id} not moved to workspace $workspace")
                println("Window Model $windowModel")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            println("Error moving window ${windowModel.id} to workspace $workspace")
            println("Error is ${e.message}")
            println("Window Model $windowModel")
        }
    }

    fun restoreWindowStates() {
        try {
            val windowModels = getNewWindowIdsWithOldState()
            val currentDateTime = getCurrentTimeDate()
            println("Restoring window states $currentDateTime")
            windowModels.forEach { windowModel ->
                moveWindow(windowModel, windowModel.workspace)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            println("Error restoring window states")
            println("Error is ${e.message}")
        }
    }

    fun saveCurrentWindowsState() {
        try {
            val windowState = getAllWindowsRaw()
            fileUtils.createFile("wmctrl-state", windowState, "/home/ubuntu/.wmctrl/")
            val currentDateTime = getCurrentTimeDate()
            println("Saved current windows state $currentDateTime")
        } catch (e: Exception) {
            e.printStackTrace()
            println("Error saving current windows state")
            println("Error is ${e.message}")
        }
    }

    fun getNewWindowIdsWithOldState(): List<WindowModel> {
        val currentWindows = getAllWindows()
        val oldWindows = fileUtils.readFile("wmctrl-state", "/home/ubuntu/.wmctrl/").rawWindowStateToWindowModel()

        val newWindows = mutableListOf<WindowModel>()

        for (oldWindow in oldWindows) {
            for (currentWindow in currentWindows) {
                if (oldWindow.applicationId == currentWindow.applicationId &&
                    oldWindow.title == currentWindow.title
                ) {
                    newWindows.add(
                        WindowModel(
                            id = currentWindow.id,
                            workspace = oldWindow.workspace,
                            applicationId = oldWindow.applicationId,
                            title = oldWindow.title
                        )
                    )
                }
            }
        }

        return newWindows.sortedBy { it.workspace }
    }

    private fun getCurrentTimeDate(): String {
        val timestamp = System.currentTimeMillis()
        val formatTimeStamp = java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return formatTimeStamp.format(Date(timestamp))
    }

    @Deprecated("This is not used anymore we are creating files directly from kotlin io")
    fun createWmctrlDirectory() {
        try {
            val createDirectoryCommand = CommandModel(
                "mkdir",
                listOf(".wmctrl"),
                workingDirectory
            )

            val result = commandExecutor.execute(createDirectoryCommand)

            if (result.error.isEmpty() && result.exitCode == 0) {
                println("Created .wmctrl directory")
                saveCurrentWindowsState()
            } else {
                println("Error creating .wmctrl directory")
                println("Error is ${result.error}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            println("Error creating .wmctrl directory")
            println("Error ${e.message}")
        }
    }
}
