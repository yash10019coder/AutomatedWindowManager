fun main(args: Array<String>) {
    val wmctrlClient = WmctrlClient()

    if (args.size != 1) {
        printHelp()
        return
    }
    val arg = args[0]

    when (arg) {
        "--save" -> {
            wmctrlClient.saveCurrentWindowsState()
        }
        "--restore" -> {
            wmctrlClient.restoreWindowStates()
        }
        "--list" -> {
            println("Window states:")
            wmctrlClient.getAllWindows().forEach { windowModel ->
                println(windowModel)
            }
        }
        "--printnewwindowidwitholdstate" -> {
            println("Window IDs:")
            wmctrlClient.getNewWindowIdsWithOldState().forEach { windowModel ->
                println(windowModel)
            }
        }
        else -> {
            println("Invalid argument $arg")
            printHelp()
        }
    }

}

fun printHelp() {
    println("Automated Workspace Manager for Linux 1.0")
    println("Usage: AutomatedWindowManager --save | --restore| --list | --printnewwindowidwitholdstate")
}
