import com.sheu.aoc.util.readEntireFile
import java.nio.file.Paths


fun main() {
    println(Paths.get("").toAbsolutePath().toString())
    val input = readEntireFile("day1.txt")
    val result = input.map { it.filter { c -> c.isDigit() } }.map { "${it.first()}${it.last()}" }.sumOf { it.toInt() }
    println(result)
    val numbers = input.map { it.extractDigit() }.map { "${it.first()}${it.last()}" }
    val sum = numbers.sumOf { it.toInt() }
    println(sum)


}


fun String.extractDigit(): String {
    val digits = listOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    val digitToWord = mapOf(
        "zero" to 0, "one" to 1, "two" to 2,
        "three" to 3, "four" to 4, "five" to 5,
        "six" to 6, "seven" to 7, "eight" to 8,
        "nine" to 9
    )
    val wordToIndex =
        digits.map {
            digitToWord[it]!! to Regex(it).findAll(this)
                .map { mr -> mr.range.first }.toList()
        }
            .toFlatMap()

    val chars =
        (mapIndexed { i, it -> i to it }).filter { it.second.isDigit() }.map {
            it.second.toString().toInt() to it.first
        }
    val combined = (wordToIndex + chars).sortedBy { it.second }.map { it.first }
    return combined.joinToString("")


}

private fun List<Pair<Int, List<Int>>>.toFlatMap(): List<Pair<Int, Int>> =
    map { r -> r.second.map { i -> r.first to i } }.flatten()

