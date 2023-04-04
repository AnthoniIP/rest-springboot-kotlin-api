package com.ipsoft.restspringbootkotlinapi.math.controller

import com.ipsoft.restspringbootkotlinapi.math.exceptions.UnsupportedMathOperatorException
import com.ipsoft.restspringbootkotlinapi.math.model.CalculateRequest
import com.ipsoft.restspringbootkotlinapi.math.model.CalculateResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MathController {

    @PostMapping("/calculate")
    fun calculate(
        @RequestBody operation: CalculateRequest,
    ) = CalculateResponse(evaluateExpression(operation.expression).toString())

    private fun evaluateExpression(expression: String): Double {

        if (expression.containsInvalidMathCharacters()) throw UnsupportedMathOperatorException()


        val numbers = mutableListOf<Double>()
        val operators = mutableListOf<Char>()

        val tokens = expression.split(Regex("(?<=[\\+\\-%/])|(?=[\\+\\-%/])"))

        tokens.forEach { token ->
            when {
                token.matches(Regex("[0-9]+")) -> numbers.add(token.toDouble())
                token.matches(Regex("[\\+\\-%/]")) -> {
                    while (operators.isNotEmpty() && precedence(token[0]) <= precedence(operators.last())) {
                        val secondOperand = numbers.removeLastOrNull() ?: 0.0
                        val firstOperand = numbers.removeLastOrNull() ?: 0.0
                        val operator =
                            operators.removeLastOrNull() ?: throw IllegalArgumentException("Invalid operator")
                        numbers.add(executeOperation(operator, firstOperand, secondOperand))
                    }
                    operators.add(token[0])
                }
            }
        }

        while (operators.isNotEmpty()) {
            val secondOperand = numbers.removeLastOrNull() ?: 0.0
            val firstOperand = numbers.removeLastOrNull() ?: 0.0
            val operator = operators.removeLastOrNull() ?: throw IllegalArgumentException("Invalid operator")
            numbers.add(executeOperation(operator, firstOperand, secondOperand))
        }

        return numbers.lastOrNull() ?: 0.0
    }

    private fun executeOperation(operator: Char, firstOperand: Double, secondOperand: Double): Double {
        return when (operator) {
            '+' -> firstOperand + secondOperand
            '-' -> firstOperand - secondOperand
            '*' -> firstOperand * secondOperand
            '/' -> firstOperand / secondOperand
            '%' -> firstOperand % secondOperand
            else -> throw IllegalArgumentException("Invalid operator")
        }
    }

    private fun precedence(operator: Char): Int {
        return when (operator) {
            '+' -> 1
            '-' -> 1
            '*' -> 2
            '/' -> 2
            '%' -> 2
            else -> throw IllegalArgumentException("Invalid operator")
        }
    }

    private fun String.containsInvalidMathCharacters(): Boolean {
        val allowedChars = "1234567890+-/*%"

        if (this.isEmpty()) return false

        for (char in this) {
            if (char !in allowedChars) {
                return true
            }
        }

        return false
    }
}