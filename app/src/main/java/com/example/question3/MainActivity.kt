package com.example.question3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextNumber1: EditText
    private lateinit var editTextNumber2: EditText
    private lateinit var spinnerOperation: Spinner
    private lateinit var buttonCalculate: Button
    private lateinit var buttonConvertCurrency: Button
    private lateinit var textViewResult: TextView

    private var operation: String = "+"

    private val operations = arrayOf("+", "-", "*", "/")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextNumber1 = findViewById(R.id.editTextNumber1)
        editTextNumber2 = findViewById(R.id.editTextNumber2)
        spinnerOperation = findViewById(R.id.spinnerOperation)
        buttonCalculate = findViewById(R.id.buttonCalculate)
        buttonConvertCurrency = findViewById(R.id.buttonConvertCurrency)
        textViewResult = findViewById(R.id.textViewResult)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, operations)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerOperation.adapter = adapter

        spinnerOperation.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                operation = operations[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                operation = operations[0]
            }
        }

        buttonCalculate.setOnClickListener {
            calculateAndDisplayResult()
        }

        buttonConvertCurrency.setOnClickListener {
            val result = calculateAndDisplayResult()
            if (result != null) {
                sendResultToCurrencyConverter(result)
            }
        }
    }

    private fun calculateAndDisplayResult(): Double? {
        val number1Str = editTextNumber1.text.toString()
        val number2Str = editTextNumber2.text.toString()

        return if (number1Str.isNotEmpty() && number2Str.isNotEmpty()) {
            val number1 = number1Str.toDouble()
            val number2 = number2Str.toDouble()
            val result = calculate(number1, number2, operation)
            textViewResult.text = String.format("Result: %.2f", result)
            result
        } else {
            Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show()
            null
        }
    }

    private fun calculate(number1: Double, number2: Double, operation: String): Double {
        return when (operation) {
            "+" -> number1 + number2
            "-" -> number1 - number2
            "*" -> number1 * number2
            "/" -> if (number2 != 0.0) number1 / number2 else 0.0
            else -> 0.0
        }
    }

    private fun sendResultToCurrencyConverter(result: Double) {
        val intent = Intent(this, MainActivity2::class.java).apply {
            putExtra("RESULT_KEY", result.toString())
        }
        startActivity(intent)
    }
}
