package model


data class CommandModel(
    val commandName: String,
    val commandArgs: List<String>,
    val workingDirectory: String
)