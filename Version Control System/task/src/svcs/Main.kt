package svcs

import java.io.File

fun main(args: Array<String>) {
    val svcs = SVCS(args)
    svcs.createDir()

    if (args[0] == "config") {
        svcs.configName()
    } else if (args[0] == "add") {
        svcs.addTrackFile()
    }
}

class SVCS(private val args: Array<String>) {
    private val root = File("./")
    private val vcs = File("./vcs")
    private val config = vcs.resolve("config.txt")
    private val index = vcs.resolve("index.txt")

    fun createDir() {
        if (!vcs.exists()) vcs.mkdir()
        if (!config.exists()) config.createNewFile()
        if (!index.exists()) {
            index.createNewFile()
        }

    }

    fun configName() {
        /*
        1. If input without arguments:
                if (config.txt contains name)
                    print(The username is ${username from config.txt})
                else if (config.txt is empty)
                    print(Please, tell me who you are.)
           if (input with arguments)
                overwrite name in file
                print(The username is ${new name from file}
         */
        if (args.size == 1) {
            if (config.readText().isEmpty()) println("Please, tell me who you are.")
            else println("The username is ${config.readText()}.")
        } else {
            config.writeText(args[1])
            println("The username is ${config.readText()}.")
        }
    }

    fun addTrackFile() {
        /*
        1. if (args.size == 1) {
                if (config.
         */
        val listFiles = root.listFiles()
        when {
            (args.size == 1) -> {
                if (index.readText().isEmpty()) {
                    println("Add a file to the index.")
                }
            }
        }
    }
}