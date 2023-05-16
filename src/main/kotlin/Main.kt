fun main(args: Array<String>) {
    val wmctrlClient = WmctrlClient()

    if (args.size != 1) {
        println("Automated Workspace Manager for Linux 1.0")
        println("Usage: wmctrl --save|--restore")
        return
    }
    val arg = args[0]

    if (arg == "--save") {
        wmctrlClient.saveCurrentWindowsState()
    } else if (arg == "--restore") {
        wmctrlClient.restoreWindowStates()
    }
}
