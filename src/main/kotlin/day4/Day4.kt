package day4

import com.sheu.aoc.util.readEntireFile

fun main() {
    val input = readEntireFile("day4.txt")
    val cards = input.map {
        it.toCard()
    }

    val result = cards.sumOf { it.toScores() }
    println(result)
    val mapCardToNumCopies = mutableMapOf<Card, Int>()
    cards.forEachIndexed { i, currentCard ->
        val matches = currentCard.numbersAtHand.intersect(currentCard.winningNumbers.toSet()).count()
        val duplicateCards = cards.subList(i + 1, i + 1 + matches)
        val numDuplicates = mapCardToNumCopies[currentCard] ?: 1
        mapCardToNumCopies.computeIfAbsent(currentCard) { 1}
        duplicateCards.forEach { dc ->
            mapCardToNumCopies.merge(dc, numDuplicates + 1) { v, _ -> v + numDuplicates }
        }

    }
    val result2 = mapCardToNumCopies.values.reduce { acc, i -> acc + i }
    println(result2)

}

private fun String.toCard(): Card {
    val (cardNumber, numbers) = split(":")
    val (winningNumbers, numbersAtHand) = numbers.trim().split("|")
    return Card(cardNumber.trim().split(" ").filter { it.isNotBlank() }[1].trim().toInt(),
        winningNumbers.trim().split(" ").filter { it.isNotBlank() }.map { it.trim().toInt() },
        numbersAtHand.trim().split(" ").filter { it.isNotBlank() }.map { it.trim().toInt() })
}
