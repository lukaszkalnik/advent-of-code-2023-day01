import okio.FileSystem
import okio.Path.Companion.toPath

fun main(args: Array<String>) {
    val input = FileSystem.SYSTEM.read("input.txt".toPath()) {readUtf8()}.dropLast(1).split("\n")

   val sumOfCalibrationValues = input.sumOf { line ->
        val firstDigit = line.asIterable().first { it.isDigit() }
        val lastDigit = line.asIterable().last { it.isDigit() }
        val numberString = "$firstDigit$lastDigit"
        numberString.toInt()
    }

    println(sumOfCalibrationValues)
}
