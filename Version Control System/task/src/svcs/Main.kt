package svcs

import svcs.Command.*
import java.io.File

fun main(args: Array<String>) {
    val svcs = SVCS(args)
    with(svcs) {
        createDir()
        readCommand()
    }
}

enum class Command(val command: String, val description: String) {
    CONFIG("config", "Get and set a username."),
    ADD("add", "Add a file to the index."),
    LOG("log", "Show commit logs."),
    COMMIT("commit", "Save changes."),
    CHECKOUT("checkout", "Restore a file."),
    HELP(
        "--help", "These are SVCS commands:\n" +
                "config     Get and set a username.\n" +
                "add        Add a file to the index.\n" +
                "log        Show commit logs.\n" +
                "commit     Save changes.\n" +
                "checkout   Restore a file."
    )
}

class SVCS(private val args: Array<String>) {
    private val root = File("./")
    private val vcs = File("./vcs")
    private val commitsFile = vcs.resolve("commits")
    private val config = vcs.resolve("config.txt")
    private val index = vcs.resolve("index.txt")
    private val log = vcs.resolve("log.txt")

    fun createDir() {

        fun createNewFile(file: File) {
            if (!file.exists()) file.createNewFile()
        }

        fun createNewDir(path: File) {
            if (!path.exists()) path.mkdir()
        }
        createNewDir(commitsFile)
        createNewDir(vcs)
        createNewFile(config)
        createNewFile(index)
        createNewFile(log)
    }

    fun readCommand() {
        when (args.firstOrNull()) {
            null -> println(HELP.description)
            HELP.command -> println(HELP.description)
            CONFIG.command -> configure()
            ADD.command -> addTrackFile()
            LOG.command -> viewCommitHistory()
            COMMIT.command -> saveFileChanges()
            CHECKOUT.command -> println(CHECKOUT.description)
            else -> println("'${args.first()}' is not a SVCS command.")
        }
    }

    private fun configure() {
        if (args.size == 1) {
            if (config.readText().isEmpty()) println("Please, tell me who you are.")
            else println("The username is ${config.readText()}.")
        } else {
            config.writeText(args[1])
            println("The username is ${config.readText()}.")
        }
    }

    private fun addTrackFile() {
        when {
            args.size == 1 -> {
                if (index.readText().isEmpty()) {
                    println("Add a file to the index.")
                } else {
                    println("Tracked files:\n${index.readLines().joinToString("\n")}")
                }
            }

            (args.size > 1) -> {
                val fileName = args[1]
                val file = root.resolve(fileName)
                if (file.exists()) {
                    index.appendText(fileName + "\n")
                    println("The file '$fileName' is tracked.")
                } else println("Can't find '$fileName'.")
            }
        }
    }

    private fun saveFileChanges() {
        fun generatePassword(): String {
            val chars = ('a'..'z') + ('0'..'9')
            return List(8) { chars.random() }.joinToString("")
        }

        if (args.size == 1) {
            println("Message was not passed.")
            return
        } else {
            if (index.readText().isBlank()) {
                println("Nothing to commit.")
                return
            }
            val message = args[1]
            val commitDir = vcs.resolve(generatePassword())
            commitDir.mkdir()

            index.forEachLine {
                if (it.isNotBlank()) {
                    val destination = commitDir.resolve(it)
                    File(it).copyTo(destination)
                }
            }

            val isFirstCommit = log.readText().isBlank()
            log.appendText(if (!isFirstCommit) "\n****\n" else "")
            log.appendText(
                """
            commit ${commitDir.name}
            Author: ${config.readText()}
            $message
        """.trimStart()
            )
            println("Changes are committed.")
        }
    }

    private fun viewCommitHistory() {
        if (log.readText().isBlank()) println("No commits yet.") else {
            val commits = log.readText().split("****").map { it.trimIndent() }
//            println("val commits = ${commits.joinToString()}")
            for (i in commits.lastIndex downTo 0) println(commits[i] + "\n")
        }
    }
}