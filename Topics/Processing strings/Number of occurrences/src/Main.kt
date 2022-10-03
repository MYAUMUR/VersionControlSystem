fun main() {
    val string = readln().replace(" ", "").replace(readln(), " ")
    var count = 0
    for (i in string) {
        if (i.isWhitespace()) count++
    }
    println(count)
}