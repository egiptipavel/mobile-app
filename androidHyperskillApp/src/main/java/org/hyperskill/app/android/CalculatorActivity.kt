package org.hyperskill.app.android

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.udojava.evalex.Expression.ExpressionException
import org.hyperskill.app.android.databinding.ActivityCalculatorBinding
import org.hyperskill.app.calculator.CalculatorCore

const val CALCULATION_LIST = "CALCULATION_LIST"


class CalculatorActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityCalculatorBinding

    private val calculatorCore = CalculatorCore()

    private val calculationList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btn0.setOnClickListener { addSymbol('0') }
        viewBinding.btn1.setOnClickListener { addSymbol('1') }
        viewBinding.btn2.setOnClickListener { addSymbol('2') }
        viewBinding.btn3.setOnClickListener { addSymbol('3') }
        viewBinding.btn4.setOnClickListener { addSymbol('4') }
        viewBinding.btn5.setOnClickListener { addSymbol('5') }
        viewBinding.btn6.setOnClickListener { addSymbol('6') }
        viewBinding.btn7.setOnClickListener { addSymbol('7') }
        viewBinding.btn8.setOnClickListener { addSymbol('8') }
        viewBinding.btn9.setOnClickListener { addSymbol('9') }

        viewBinding.btnDot.setOnClickListener { addSymbol('.') }

        viewBinding.btnMinus.setOnClickListener { addSymbol('-') }
        viewBinding.btnPlus.setOnClickListener { addSymbol('+') }
        viewBinding.btnDiv.setOnClickListener { addSymbol('/') }
        viewBinding.btnMult.setOnClickListener { addSymbol('*') }

        viewBinding.btnLeftBracket.setOnClickListener { addSymbol('(') }
        viewBinding.btnRightBracket.setOnClickListener { addSymbol(')') }

        viewBinding.btnDel.setOnClickListener { deleteSymbol() }
        viewBinding.btnAllClean.setOnClickListener { clear() }
        viewBinding.btnEqual.setOnClickListener { solve() }

        viewBinding.btnHistory.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            intent.putStringArrayListExtra(CALCULATION_LIST, calculationList)
            startActivity(intent)
        }
    }

    private fun addSymbol(symbol: Char) {
        val result: String = viewBinding.result.text.toString()
        if (result.isNotEmpty()) {
            if (symbol == '+' || symbol == '-' || symbol == '*' || symbol == '/') {
                viewBinding.input.text = result.plus(symbol)
            } else {
                viewBinding.input.text = symbol.toString()
            }
            viewBinding.result.text = ""
        } else {
            viewBinding.input.append(symbol.toString())
        }
    }

    private fun clear() {
        viewBinding.input.text = ""
        viewBinding.result.text = ""
    }

    private fun deleteSymbol() {
        val input = viewBinding.input.text
        if (input.isNotEmpty()) {
            viewBinding.input.text = input.substring(0, input.length - 1)
        }
    }

    private fun solve() {
        val expression: String = viewBinding.input.text.toString()
        if (expression.isNotEmpty()) {
            try {
                val result: String = calculatorCore.evaluateExpression(expression)
                calculationList.add("$expression=$result")
                viewBinding.input.text = ""
                viewBinding.result.text = result
            } catch (exception: ExpressionException) {
            }
        }
    }
}