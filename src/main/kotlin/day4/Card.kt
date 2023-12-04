package day4

import kotlin.math.pow

data class Card(val num: Int, val winningNumbers: List<Int>, val numbersAtHand: List<Int>) {
    fun toScores(): Int {
        val intersect = winningNumbers.intersect(numbersAtHand.toSet())
        return if(intersect.isNotEmpty())
            2.toDouble().pow((intersect.size - 1).toDouble()).toInt()
        else 0
    }
}