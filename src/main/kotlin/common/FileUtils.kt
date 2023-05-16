package common

import java.io.File

class FileUtils {
    fun createFile(fileName: String, content: String, path: String) {
        try {
            val file = File(path + fileName)
            file.writeText(content)
            println("File $fileName created in path $path")
        } catch (e: Exception) {
            e.printStackTrace()
            println("Error creating file $fileName in path $path")
            println("Error is ${e.message}")
        }
    }
    fun readFile(fileName: String, path: String): String {
        return try {
            val file = File(path + fileName)
            file.readText()
        } catch (e: Exception) {
            e.printStackTrace()
            println("Error reading file $fileName in path $path")
            println("Error is ${e.message}")
            ""
        }
    }
}
