import okio.FileSystem
import okio.Path.Companion.toPath

fun main(args: Array<String>) {
    val input = FileSystem.SYSTEM.read("input.txt".toPath()) { readUtf8() }.dropLast(1).split("\n")

    val sumOfCoordinates = input.sumOf { line ->
        val digits = line.asDigits()
        digits.first() * 10 + digits.last()
    }

    println(sumOfCoordinates)
}

val digitWords = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

fun String.asDigits(): List<Int> {
    val digits = mutableListOf<Int>()

    for (i in indices) {
        if (this[i].isDigit()) {
            digits += this[i].digitToInt()
            continue
        }

        findDigitWord(i, digits)
    }

    return digits
}

private fun String.findDigitWord(i: Int, digits: MutableList<Int>) {
    for (digitWordIndexed in digitWords.withIndex()) {
        val digitWord = digitWordIndexed.value
        val substringLength = digitWord.length

        if (i + substringLength <= length &&
            substring(i, i + substringLength) == digitWord
        ) {
            digits += (digitWordIndexed.index + 1)
            break
        }
    }
}
