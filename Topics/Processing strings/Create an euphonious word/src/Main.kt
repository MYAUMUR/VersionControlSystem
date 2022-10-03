const val VOWELS = "aeiouy"

fun main() {
    var charNeed = 0
    var vowels = 0
    var consonant = 0
    val inputValue = readln()

    for (i in inputValue) {
        if (VOWELS.contains(i)) {
            consonant = 0
            vowels++
        } else {
            vowels = 0
            consonant++
        }
        if (consonant == 3 || vowels == 3) {
            charNeed++
            consonant = 1
            vowels = 1
        }
    }

    println(charNeed)
}