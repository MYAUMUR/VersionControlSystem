fun main() {
    val ticket = readln().toList()
    val equals = sumChar(ticket[0], ticket[1], ticket[2]) == sumChar(
        ticket[ticket.lastIndex],
        ticket[ticket.lastIndex - 1],
        ticket[ticket.lastIndex - 2]
    )
    println(if (equals) "Lucky" else "Regular")
}

fun toIntFromChar(char: Char): Int {
    return char.toString().toInt()
}

fun sumChar(one: Char, two: Char, three: Char): Int {
    return toIntFromChar(one) + toIntFromChar(two) + toIntFromChar(three)
}