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

}

fun main(args: Array<String>) {
    val wmctrlTest = WmctrlTest(WmctrlClient())
    wmctrlTest.testSaveWindowState()
}
