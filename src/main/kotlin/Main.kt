import okio.FileSystem
import okio.Path.Companion.toPath

fun main(args: Array<String>) {
    val input = FileSystem.SYSTEM.read("input.txt".toPath()) { readUtf8() }.dropLast(1).split("\n")

    val sumOfCoordinates = input.sumOf { line ->
        val firstDigit = line.firstDigit(digitWords)
        val lastDigit = line.reversed().firstDigit(digitWords.map { it.reversed() })
        firstDigit * 10 + lastDigit
    }

    println(sumOfCoordinates)
}

val digitWords = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

fun String.firstDigit(digitWords: List<String>): Int {
    for (i in indices) {
        if (this[i].isDigit()) return this[i].digitToInt()
        findDigitWord(i, digitWords)?.let { return it }
    }
    return 0 // never happens with input data
}

private fun String.findDigitWord(i: Int, digitWords: List<String>): Int? {
    for (digitWordIndexed in digitWords.withIndex()) {
        val digitWord = digitWordIndexed.value
        if (substring(i).startsWith(digitWord)) return digitWordIndexed.index + 1
    }
    return null
}
