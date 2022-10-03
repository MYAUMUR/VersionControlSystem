fun main() {
    val text = readln().toMutableList()
    var isAlpha = true
    for (i in 0..text.size - 2) {
        if (text[i].code + 1 != text[i + 1].code) {
            isAlpha = false
        }
    }
    println(isAlpha)
}