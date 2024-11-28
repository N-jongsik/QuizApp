package com.example.quizapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)

        // Intent에서 닉네임 값 가져오기
        val nickname = intent.getStringExtra("NICKNAME")
        val textView3 = findViewById<TextView>(R.id.textView3)

        textView3.text = "환영합니다, $nickname!"
    }
}