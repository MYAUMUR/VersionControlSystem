fun main() {
    val link = readln()
    val url = link
        .substringAfter('?')
        .replace("=&", "=not found&")
        .split("&")
        .associate { it.split("=")[0] to it.split("=")[1] }
    url.forEach {
        println("${it.key} : ${it.value}")
    }
    println(url)
    if (url.containsKey("pass")) println("password : ${url.getValue("pass")}")
}