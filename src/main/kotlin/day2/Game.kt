package day2

import kotlin.time.times

data class Game(val num: Int, val sets: List<Map<String, Int>>) {
    fun maxPossible(): Long {
        val greens = sets.map { it["green"] }.filterNotNull().max().toLong()
        val reds = sets.map { it["red"] }.filterNotNull().max().toLong()
        val blue = sets.map { it["blue"] }.filterNotNull().max().toLong()
        return greens * reds * blue
    }

}

