import org.junit.Assert.assertEquals
import org.junit.Test

class MainKtTest {

    @Test
    fun calculateVKCommission() {
        val cardType = "VK Pay"
        val prevSum = 10_000.0
        val currentSum = 800.0
        val result = calculateCommission(cardType, prevSum, currentSum)

        assertEquals(0.0, result, 0.001)
    }

    @Test
    fun calculateCommissionThrow() {
        val cardType = "VK Pay"
        val prevSum = 10_000.0
        val currentSum = 18_000.0
        var throws = false
        try {
            calculateCommission(cardType, prevSum, currentSum)
        } catch (err: Exception) {
            throws = err.message.toString().equals("Превышен лимит перевода по карте")
        }

        assertEquals(true, throws)
    }

    @Test
    fun calculateVisaCommission() {
        val cardType = "Visa"
        val prevSum = 0.0
        val currentSum = 18_000.0
        val result = calculateCommission(cardType, prevSum, currentSum)

        assertEquals(135.0, result, 0.001)
    }

    @Test
    fun calculateMaestroNoCommission() {
        val cardType = "Maestro"
        val prevSum = 1000.0
        val currentSum = 10_000.0
        val result = calculateCommission(cardType, prevSum, currentSum)

        assertEquals(1.0, result, 0.001)
    }

    @Test
    fun calculateMaestroCommission() {
        val cardType = "Maestro"
        val prevSum = 80_000.0
        val currentSum = 1000.0
        val result = calculateCommission(cardType, prevSum, currentSum)

        assertEquals(26.0, result, 0.001)
    }


    @Test
    fun checkLimitsVKNoCommission() {
        val cardType = "VK Pay"
        val prevSum = 15_000.0
        val currentSum = 1_000.0

        val result = checkLimits(cardType, prevSum, currentSum)
        assertEquals(true, result)
    }

    @Test
    fun checkLimitsVKCommission() {
        val cardType = "VK Pay"
        val prevSum = 15_000.0
        val currentSum = 35_000.0

        val result = checkLimits(cardType, prevSum, currentSum)
        assertEquals(false, result)
    }

    @Test
    fun checkLimitsVisaNoCommission() {
        val cardType = "Visa"
        val prevSum = 15_000.0
        val currentSum = 35_000.0

        val result = checkLimits(cardType, prevSum, currentSum)
        assertEquals(true, result)
    }

    @Test
    fun checkLimitsVisaCommissionСurrentSum() {
        val cardType = "Visa"
        val prevSum = 10_000.0
        val currentSum = 160_000.0

        val result = checkLimits(cardType, prevSum, currentSum)
        assertEquals(false, result)
    }

    @Test
    fun checkLimitsVisaCommissionPrevSum() {
        val cardType = "Visa"
        val prevSum = 10_000.0
        val currentSum = 600_000.0

        val result = checkLimits(cardType, prevSum, currentSum)
        assertEquals(false, result)
    }
}