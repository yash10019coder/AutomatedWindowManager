class WmctrlTest(private val wmctrlClient: WmctrlClient) {
    fun testListWindows() {
        val windows = wmctrlClient.getAllWindows()
        windows.forEach { window ->
            println("${window.id} ${window.workspace} ${window.applicationId} ${window.title}")
        }
    }

    fun testSaveWindowState() {
        wmctrlClient.saveCurrentWindowsState()
    }

    fun testMoveWindow() {
        val currentWindows = wmctrlClient.getAllWindows()
        currentWindows.forEach { window ->
            println("${window.id} ${window.workspace} ${window.applicationId} ${window.title}")
            if (window.applicationId == "jetbrains-idea.jetbrains-idea") {
                wmctrlClient.moveWindow(window, 1)
            }
        }
    }

    fun testGetNewWindowIdsWithOdlState() {
        val newWindows = wmctrlClient.getNewWindowIdsWithOldState()
        newWindows.forEach { window ->
            println("${window.id} ${window.workspace} ${window.applicationId} ${window.title}")
        }
    }

    fun testRestoreWindowState() {
        wmctrlClient.restoreWindowStates()
    }
}

fun main(args: Array<String>) {
    val wmctrlTest = WmctrlTest(WmctrlClient())
//    wmctrlTest.testSaveWindowState()
//    wmctrlTest.testGetNewWindowIdsWithOdlState()
}
