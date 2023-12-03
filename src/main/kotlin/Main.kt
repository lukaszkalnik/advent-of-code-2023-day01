import okio.FileSystem
import okio.Path.Companion.toPath

fun main(args: Array<String>) {
    val input = FileSystem.SYSTEM.read("input.txt".toPath()) { readUtf8() }.dropLast(1).split("\n")

    input.forEach { line ->
        val digits = line.asDigits()

        println(digits)
    }
}

val digitWords = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

fun String.asDigits(): List<Char> {
    val digits = mutableListOf<Char>()

    for (i in indices) {
        if (this[i].isDigit()) {
            digits += this[i]
            continue
        }

        findDigitWord(i, digits)
    }

    return digits
}

private fun String.findDigitWord(i: Int, digits: MutableList<Char>) {
    for (digitWordIndexed in digitWords.withIndex()) {
        val digitWord = digitWordIndexed.value
        val substringLength = digitWord.length

        if (i + substringLength <= length &&
            substring(i, i + substringLength) == digitWord
        ) {
            digits += (digitWordIndexed.index + 1).toString().asIterable().first()
            break
        }
    }
}
