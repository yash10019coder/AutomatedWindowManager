package command

data class CommandResult(
    val exitCode: Int,
    val output: String,
    val error: String
)
