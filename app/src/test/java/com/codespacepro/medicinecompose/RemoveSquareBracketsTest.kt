package com.codespacepro.medicinecompose

import com.codespacepro.medicinecompose.component.removeSquareBrackets
import org.junit.Test
import org.junit.Assert.*

class RemoveSquareBracketsTest {

    @Test
    fun `test removeSquareBrackets with brackets in the middle`() {
        val input = "This is a [sample] string"
        val expectedOutput = "This is a sample string"
        assertEquals(expectedOutput, removeSquareBrackets(input))
    }

    @Test
    fun `test removeSquareBrackets with brackets at the start`() {
        val input = "[Sample] string with brackets"
        val expectedOutput = "Sample string with brackets"
        assertEquals(expectedOutput, removeSquareBrackets(input))
    }

    @Test
    fun `test removeSquareBrackets with brackets at the end`() {
        val input = "String with brackets[]"
        val expectedOutput = "String with brackets"
        assertEquals(expectedOutput, removeSquareBrackets(input))
    }
}
