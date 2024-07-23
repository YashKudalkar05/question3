package com.example.question3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.question3.MainActivity
import com.example.question3.MainActivity2
import com.example.question3.R

class MainActivity3 : AppCompatActivity() {

    private lateinit var buttonOpenCalculator: Button
    private lateinit var buttonOpenConverter: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val textViewInfo = findViewById<TextView>(R.id.textViewInfo)
        val textViewDescription = findViewById<TextView>(R.id.textViewDescription)
        buttonOpenCalculator = findViewById(R.id.buttonOpenCalculator)
        buttonOpenConverter = findViewById(R.id.buttonOpenConverter)

        // Set text for the TextViews
        textViewInfo.text = "Welcome to the Info Screen!"
        textViewDescription.text = "This app includes a Calculator and a Currency Converter. Use the Calculator to perform basic arithmetic operations and the Currency Converter to convert between different currencies."

        buttonOpenCalculator.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        buttonOpenConverter.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}
