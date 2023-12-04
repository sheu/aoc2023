package day2

import com.sheu.aoc.util.readEntireFile

fun main() {
     val lines  = readEntireFile("day2.txt")
    val gameList  = lines.map { it ->
        val (a, b) = it.split(":")
        a to b.split(";")
            .map {
                it.split(",").toMap()
            }
    }.map { it.toGame() }
    val part1 =   gameList.filter { g -> g.isValid(red = 12, green = 13, blue = 14) }.sumOf { it.num }
    println(part1)
    // part 2
    val part2 = gameList.sumOf { it.maxPossible() }
    println(part2)

    val lines2  = readEntireFile("day2.txt").map { it.split(":") }
    println(lines2)



}

private fun Game.isValid(red: Int, green: Int, blue: Int): Boolean {
 for (a in sets) {
     if (a["red"] != null && a["red"]!! > red) return false
     if (a["green"] != null && a["green"]!! > green) return false
     if (a["blue"] != null &&a ["blue"]!! > blue) return false
 }

 return true
}

private fun  Pair<String, List<Map<String, Int>>>.toGame() = Game(first.split(" ").last().toInt(), second)

private fun  List<String>.toMap() = associate {
    val (a, b) = it.trim().split(" ")
    b to a.toInt()
}

