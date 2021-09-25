package dev.goodwin.learn.projecteuler

fun main() {
    question1()
    question2()
}

fun question1() {
    val max = 1000 - 1
    var sumOf3s = 0
    for (i in 3..max step 3) sumOf3s += i
    var sumOf5s = 0
    for (i in 5..max step 5) sumOf5s += i
    var sumOf15s = 0
    for (i in 15..max step 15) sumOf15s += i
    val answer = sumOf3s + sumOf5s - sumOf15s
    println("Q1: $answer")
}

// Improvement idea (for less space): Rather than using a list, use two variables
fun question2() {
    val max = 4_000_000
    val fibs = mutableListOf(1, 2)
    var fibIndex = 1
    var sumOfEvenFibs = 0
    while (fibs[fibIndex] <= max) {
        if (fibs[fibIndex] % 2 == 0) sumOfEvenFibs += fibs[fibIndex]
        val next = fibs[fibIndex] + fibs[fibIndex - 1]
        fibs.add(next)
        fibIndex++
    }
    println("Q2: $sumOfEvenFibs")
}
