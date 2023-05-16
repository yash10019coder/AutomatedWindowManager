package command

import model.CommandModel

interface CommandExecutor {
    fun execute(command: CommandModel): CommandResult
}