package configs

import java.util.*

class Config {
    companion object{
        const val PATH = "src/main/resources/"
        const val FILE_NAME = "config.properties"
        const val FILE_PATH = PATH + FILE_NAME
    }

    val properties = Properties()
    init {
        properties.load(javaClass.classLoader.getResourceAsStream(FILE_NAME))
    }

    fun getProperty(key: String): String {
        return properties.getProperty(key)
    }

    fun setProperty(key: String, value: String) {
        properties.setProperty(key, value)
    }

    fun save() {
//        properties.store(javaClass.classLoader.getResourceAsStream(FILE_NAME), null)
    }
}