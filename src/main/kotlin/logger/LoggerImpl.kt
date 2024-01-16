package logger

class LoggerImpl:Logger {
    override fun log(message: String) {
        println(message)
    }

    override fun logError(message: String) {
        println("Error: $message")
    }

    override fun logError(exception: Exception) {
        println("Error: ${exception.message}")
        exception.printStackTrace()
    }

    override fun logError(exception: Exception, message: String) {
        println("Error: $message")
        println("Error: ${exception.message}")
        exception.printStackTrace()
    }

    override fun logInfo(message: String) {
        println("Info: $message")
    }

    override fun logDebug(message: String) {
        println("Debug: $message")
    }
}