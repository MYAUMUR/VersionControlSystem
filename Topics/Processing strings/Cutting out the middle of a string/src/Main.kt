fun main() {
    val text = readln()
    val middle = text.length / 2
    if (text.length % 2 == 0) {
        print(text.substring(0, middle - 1))
        println(text.substring(middle + 1, text.length))
    } else {
        println(text.substring(0, middle) + text.substring(middle + 1, text.length))
    }
}