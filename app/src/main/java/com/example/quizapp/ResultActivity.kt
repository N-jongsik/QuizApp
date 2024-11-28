package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        val correct = intent.getIntExtra("CORRECT",0)
        val nickname = intent.getStringExtra("NICKNAME")
        val textView = findViewById<TextView>(R.id.textView)

        textView.text =  "${nickname}님의 맞은 갯수는 ${correct}개 입니다!"


        val buttonRetry = findViewById<Button>(R.id.buttonRetry)

        buttonRetry.setOnClickListener {
            navigateToQuizScreen()
        }

        val buttonNext = findViewById<Button>(R.id.buttonNext)

        buttonNext.setOnClickListener {
            navigateToMainScreen()
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