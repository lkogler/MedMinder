package net.laurakogler.medminder

import org.jetbrains.spek.api.Spek
import kotlin.test.assertEquals

class ExampleUnitTest : Spek() {
    init {
        given("Two numbers") {
            val firstNumber = 3
            val secondNumber = 5
            on("adding the numbers") {
                val result = firstNumber + secondNumber
                it("should return the correct sum") {
                    assertEquals(8, result)
                }
            }
        }
    }
}
