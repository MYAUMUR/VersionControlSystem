fun main() {
    val (s, n) = readln().split(" ")
    println("${s.drop(n.toInt())}${s.take(n.toInt())}")
}