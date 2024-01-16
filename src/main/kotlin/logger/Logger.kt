package logger

interface Logger {
    fun log(message: String)

    fun logError(message: String)

    fun logError(exception: Exception)

    fun logError(exception: Exception, message: String)

    fun logInfo(message: String)

    fun logDebug(message: String)
}