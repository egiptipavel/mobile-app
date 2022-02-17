package org.hyperskill.app.calculator
import com.udojava.evalex.Expression

class CalculatorCore {
    fun evaluateExpression(expression: String): String {
        return Expression(expression).eval().toEngineeringString()
    }
}