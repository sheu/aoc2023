package day2

import kotlin.time.times

data class Game(val num: Int, val sets: List<Map<String, Int>>) {
    fun maxPossible(): Long {
        val greens = sets.mapNotNull { it["green"] }.max().toLong()
        val reds = sets.mapNotNull { it["red"] }.max().toLong()
        val blue = sets.mapNotNull { it["blue"] }.max().toLong()
        return greens * reds * blue
    }

}

