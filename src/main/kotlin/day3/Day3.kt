package day3

import com.sheu.aoc.util.readEntireFile

fun main() {
    val regex = Regex("\\d+")
    val input = readEntireFile("day3.txt")
    val validPartNumber = mutableListOf<Int>()
    input.forEachIndexed { i, s ->
        val numberRanges = regex.findAll(s)
        numberRanges.forEach {
            if(input.isValidPartNumber(i,it.range)) {
                validPartNumber.add(it.value.toInt())
            }
        }

    }
    println(validPartNumber.sum())


    val gearRatios = mutableListOf<Long>()
    input.forEachIndexed { i, s ->
        val gearRanges = Regex("\\*").findAll(s)
        gearRanges.forEachIndexed{ _, mr ->
            val ratios = input.extractGearRatios(i, mr.range.first )
            gearRatios.add(ratios)
        }

    }
    println(gearRatios.sum())
}

fun List<String>.isValidPartNumber(index: Int, range: IntRange): Boolean {
    val rangeToCheck = IntRange((range.first -1).coerceAtLeast(0),(range.last + 1).coerceAtMost(this[index].lastIndex))
    // check left and right
    if(index in 0..<lastIndex) {
        // check bottom
        if(this[index+1].hasSymbolInRange(rangeToCheck)) {
            return true
        }
    }
    if(index in 1..lastIndex) {
        // check top
        if(this[index-1].hasSymbolInRange(rangeToCheck)) {
            return true
        }
    }
    // check right
      if(this[index][rangeToCheck.first] != '.' && (!this[index][rangeToCheck.first].isDigit())) return true

    // check left
    if(this[index][rangeToCheck.last] != '.' && (!this[index][rangeToCheck.last].isDigit())) return true
    return false
}

private fun String.hasSymbolInRange(rangeToCheck: IntRange): Boolean {
    val sub = substring(rangeToCheck).replace(".", "").replace(Regex("\\d+"), "")
    return sub.isNotBlank()
}

fun List<String>.extractGearRatios(stringIndex: Int, gearIndex: Int): Long {
    val gearNumbers = mutableListOf<Int>()
    val numberRegex = Regex("\\d+")

    if (stringIndex in 0..<lastIndex) {
        // check bottom
        val gears = numberRegex.findAll(this[stringIndex + 1])
            .filter {
                isAdjacentToGear(gearIndex, it)
            }.map { mr -> mr.value.toInt() }

        gearNumbers.addAll(gears.toList())

    }
    if (stringIndex in 1..lastIndex) {
        // check top
        val gears = numberRegex.findAll(this[stringIndex - 1])
            .filter {
                isAdjacentToGear(gearIndex, it)
            }.map { mr -> mr.value.toInt() }
        gearNumbers.addAll(gears.toList())
    }
    val gears = numberRegex.findAll(this[stringIndex])
        .filter {
            gearIndex - 1 in it.range || gearIndex + 1 in it.range
        }.map { mr -> mr.value.toInt() }
    gearNumbers.addAll(gears.toList())


    return if (gearNumbers.size != 2) 0L else gearNumbers.reduce(Int::times).toLong()

}

private fun List<String>.isAdjacentToGear(
    gearIndex: Int,
    matchResult: MatchResult,
): Boolean {


    val result = gearIndex in matchResult.range ||
            gearIndex - 1 in matchResult.range ||
            gearIndex + 1 in matchResult.range

    return result
}


