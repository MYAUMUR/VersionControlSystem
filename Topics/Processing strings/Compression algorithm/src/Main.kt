fun main() {
    val input = readln().toCharArray()
    var count = 1
    var former = input.first()
    val newList = mutableListOf<String>()
    for (i in 1 until input.size) {
        if (input[i] == former) {
            count++
        } else {
            with(newList) {
                add(former.toString())
                add(count.toString())
            }
            count = 1
            former = input[i]
        }
    }
    newList.add(former.toString())
    newList.add(count.toString())
    println(newList.joinToString(""))
}