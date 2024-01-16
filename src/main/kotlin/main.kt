import kotlin.math.roundToInt

fun main() {
    println(calculateCommission("VK Pay", 10_000.0, 800.0))
//    println(calculateCommission("Mastercard", 10_000.0, 1_000.0))
//    println(calculateCommission("Maestro", 85_000.0, 300.75))
//    println(calculateCommission("Mir", currentSum = 500.0))
//    println(calculateCommission("Visa", currentSum = 15_000.0))
}

fun calculateCommission(cardType: String = "VK Pay", prevSum: Double = 0.0, currentSum: Double): Double {
    if (!checkLimits(cardType, prevSum, currentSum)) throw Exception("Превышен лимит перевода по карте")
    val commission = when (cardType) {
        "VK Pay" -> 0.0
        "Mastercard", "Maestro" -> if (prevSum + currentSum <= 75_000) 0.0 else currentSum * 0.006 + 20.0
        else -> if (currentSum * 0.0075 > 35.0) currentSum * 0.0075 else 35.0
    }
    return (commission * 100).roundToInt() / 100.0
}

fun checkLimits(cardType: String, prevSum: Double, currentSum: Double): Boolean {
    if ((cardType == "VK Pay") && ((currentSum > 15_000) || ((currentSum + prevSum) > 40_000))) return false
    if ((currentSum > 150_000) || ((currentSum + prevSum) > 600_000)) return false
    return true
}