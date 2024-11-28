package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        val buttonRetry = findViewById<Button>(R.id.buttonRetry)

        buttonRetry.setOnClickListener {
            navigateToQuizScreen()
        }
    }
    private fun navigateToMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // 현재 액티비티 종료
    }

    private fun navigateToQuizScreen() {
        val intent = Intent(this, QuizActivity::class.java)
        startActivity(intent)
        finish() // 현재 액티비티 종료
    }
}