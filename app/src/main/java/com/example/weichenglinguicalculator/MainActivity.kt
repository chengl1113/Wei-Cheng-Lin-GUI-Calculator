package com.example.weichenglinguicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get all of the buttons
        val oneButton: Button = findViewById<Button>(R.id.one_button)
        val twoButton: Button = findViewById<Button>(R.id.two_button)
        val threeButton: Button = findViewById<Button>(R.id.three_button)
        val fourButton: Button = findViewById<Button>(R.id.four_button)
        val fiveButton: Button = findViewById<Button>(R.id.five_button)
        val sixButton: Button = findViewById<Button>(R.id.six_button)
        val sevenButton: Button = findViewById<Button>(R.id.seven_button)
        val eightButton: Button = findViewById<Button>(R.id.eight_button)
        val nineButton: Button = findViewById<Button>(R.id.nine_button)
        val zeroButton: Button = findViewById<Button>(R.id.zero_button)
        val decimalButton: Button = findViewById<Button>(R.id.decimal_button)
        val plusButton: Button = findViewById<Button>(R.id.plus_button)
        val minusButton: Button = findViewById<Button>(R.id.minus_button)
        val multiplyButton: Button = findViewById<Button>(R.id.multiply_button)
        val divideButton: Button = findViewById<Button>(R.id.divide_button)
        val squareRootButton: Button = findViewById<Button>(R.id.square_root_button)
        val equalButton: Button = findViewById<Button>(R.id.equal_sign_button)

        // variable to store operation last pressed
        var operation: String = ""
        var numOne: String = ""
        var numTwo: String = ""

        // Array to store all number buttons + decimal
        val numberButtons = arrayOf(oneButton, twoButton, threeButton, fourButton, fiveButton, sixButton, sevenButton, eightButton, nineButton, zeroButton, decimalButton)

        // Array to store all operation buttons except for equal sign and square root
        val operationButtons = arrayOf(plusButton, minusButton, multiplyButton, divideButton)

        // get the EditText view
        val outputView: EditText = findViewById(R.id.number_input)

        // Get the results TextView
        val resultView: TextView = findViewById(R.id.result_view)

        // Handle pressing all numbers and decimal
        val numberButtonClickListener = View.OnClickListener { view ->
            // Get the button that was pressed
            val numberPressed = (view as Button).text.toString()
            val currentNumber = outputView.text.toString()

            when (numberPressed) {
                "1" -> { outputView.setText(currentNumber + "1") }
                "2" -> { outputView.setText(currentNumber + "2") }
                "3" -> { outputView.setText(currentNumber + "3") }
                "4" -> { outputView.setText(currentNumber + "4") }
                "5" -> { outputView.setText(currentNumber + "5") }
                "6" -> { outputView.setText(currentNumber + "6") }
                "7" -> { outputView.setText(currentNumber + "7") }
                "8" -> { outputView.setText(currentNumber + "8") }
                "9" -> { outputView.setText(currentNumber + "9") }
                "0" -> { outputView.setText(currentNumber + "0") }
                "." -> { if ('.' !in currentNumber) outputView.setText(currentNumber + ".") }
            }
        }

        // Handle pressing operation buttons except for equal sign and square root
        val operationButtonClickListener = View.OnClickListener { view ->
            // Get the button that was pressed
            when ((view as Button).text.toString()) {
                "+" -> {
                    operation = "+"
                    numOne = outputView.text.toString()
                    outputView.setText("")
                }
                "-" -> {
                    operation = "-"
                    numOne = outputView.text.toString()
                    outputView.setText("")
                }
                "*" -> {
                    operation = "*"
                    numOne = outputView.text.toString()
                    outputView.setText("")
                }
                "\\" -> {
                    operation = "/"
                    numOne = outputView.text.toString()
                    outputView.setText("")
                }
            }
        }

        // Map all number buttons to numberButtonClickListener
        numberButtons.map { button -> button.setOnClickListener(numberButtonClickListener) }

        // Map all operation buttons to operationButtonClickListener
        operationButtons.map { button -> button.setOnClickListener(operationButtonClickListener)}

        // Handle pressing the equals button
        equalButton.setOnClickListener { view: View ->
            numTwo = outputView.text.toString()
            var result:String = ""
            when (operation) {
                "+" -> { result = (numOne.toDouble() + numTwo.toDouble()).toString() }
                "-" -> { result = (numOne.toDouble() - numTwo.toDouble()).toString() }
                "*" -> { result = (numOne.toDouble() * numTwo.toDouble()).toString() }
                "/" -> { if (numTwo == "0") result = "Cannot divide by 0" else result = (numOne.toDouble() / numTwo.toDouble()).toString() }
            }
            numOne = ""
            numTwo = ""
            operation = ""
            outputView.setText("")
            resultView.setText(result)
        }

        // Handle square root operation
        squareRootButton.setOnClickListener {view: View ->
            numOne = outputView.text.toString()
            var result = ""
            if (numOne.toDouble() < 0) {
                result = "Error: negative number"
            } else {
                result = sqrt(numOne.toDouble()).toString()
            }
            numOne = ""
            numTwo = ""
            operation = ""
            outputView.setText("")
            resultView.setText(result)

        }

    }
}