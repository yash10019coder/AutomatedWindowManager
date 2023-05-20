fun main(args: Array<String>) {
    val wmctrlClient = WmctrlClient()

    if (args.size != 1) {
        printHelp()  
        return
    }
    val arg = args[0]

    if (arg == "--save") {
        wmctrlClient.saveCurrentWindowsState()
    } else if (arg == "--restore") {
        wmctrlClient.restoreWindowStates()
    } else if(arg == "--help" || arg=="-h") {
        printHelp()
    }
        
}
 fun printHelp(){
     println("Automated Workspace Manager for Linux 1.0")
     println("Usage: AutomatedWindowManager --save | --restore")
}
