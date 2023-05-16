package common

import model.CommandModel
import java.io.File
import java.io.IOException

class CommandExecutorImpl : CommandExecutor {
    override fun execute(command: CommandModel): CommandResult {
        try {
            val workingDirectory = File(command.workingDirectory)
            val formattedCommand = commandFormatter(command)

            val runtime = Runtime.getRuntime()
            val process = runtime.exec(formattedCommand, null, workingDirectory)

            val processError = process.errorStream.bufferedReader().readLines()

            return if (process.exitValue() == 0 && processError.isEmpty()) {
                CommandResult(
                    exitCode = process.exitValue(),
                    output = process.inputStream.bufferedReader().readLines().joinToString("\n"),
                    error = ""
                )
            } else {
                CommandResult(
                    exitCode = process.exitValue(),
                    output = "",
                    error = processError.joinToString("\n")
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return CommandResult(
                exitCode = -1,
                output = "",
                error = e.message ?: "Unknown error"
            )
        } catch (e: IOException) {
            e.printStackTrace()
            return CommandResult(
                exitCode = -1,
                output = "",
                error = e.message ?: "IOException"
            )
        }
    }

    private fun commandFormatter(command: CommandModel): String {
        return "${command.commandName} ${command.commandArgs.joinToString(" ")}"
    }
}
