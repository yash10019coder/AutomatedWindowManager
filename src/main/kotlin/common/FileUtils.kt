package common

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

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

    fun getAllFiles(fileName: String, path: String) {
        return try {
            val files = Files.find(Paths.get(path), 1, { p, _ -> p.fileName.toString().contains(fileName) }).sorted()
            files.forEach { println(it) }
        }catch (e: Exception) {
            e.printStackTrace()
            println("Error reading file $fileName in path $path")
            println("Error is ${e.message}")
        }
    }

    fun isFileExists(fileName: String, path: String): Boolean {
        return try {
            val file = File(path + fileName)
            file.exists()
        } catch (e: Exception) {
            e.printStackTrace()
            println("Error checking if file $fileName exists in path $path")
            println("Error is ${e.message}")
            false
        }
    }
}
