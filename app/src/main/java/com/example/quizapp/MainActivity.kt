package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

    class MainActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            // XML에서 정의한 UI 요소를 가져오기
            val startButton: Button = findViewById(R.id.button)
            val nicknameInput: EditText = findViewById(R.id.inputField)

            // 버튼 클릭 이벤트 설정
            startButton.setOnClickListener {
                val nickname = nicknameInput.text.toString()
                if (nickname.isNotEmpty()) {
                    Toast.makeText(this, "$nickname 님, 환영합니다!", Toast.LENGTH_SHORT).show()

                    // Intent 생성 및 데이터 전달
                    val intent = Intent(this, QuizActivity::class.java)
                    intent.putExtra("NICKNAME", nickname)

                    // 다른 액티비티로 이동
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "닉네임을 입력하세요!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
