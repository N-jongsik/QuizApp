package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var timerTextView: TextView
    private lateinit var option1: FrameLayout
    private lateinit var option2: FrameLayout

    private var timeLeft: Int = 5 // 타이머 시간 (초)
    private var progress: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val nickname = intent.getStringExtra("NICKNAME")
        val textView3 = findViewById<TextView>(R.id.textView3)

        textView3.text = "${nickname}님의 지식 수준 테스트!"

        // XML에서 뷰 가져오기
        progressBar = findViewById(R.id.progressBar)
        timerTextView = findViewById(R.id.textViewTimer)
        option1 = findViewById(R.id.option1)
        option2 = findViewById(R.id.option2)

        // 진행 바 초기화
        progressBar.progress = 0

        // 타이머 시작
        startTimer()

        // 선택지 클릭 이벤트 설정
        option1.setOnClickListener { checkAnswer("주꾸미") }
        option2.setOnClickListener { checkAnswer("쭈꾸미") }
    }

    private fun startTimer() {
        object : CountDownTimer(timeLeft * 1000L, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft--
                timerTextView.text = "남은 시간 : ${timeLeft}초"
                progress += 20
                progressBar.progress = progress
            }

            override fun onFinish() {
                Toast.makeText(this@QuizActivity, "시간 초과!", Toast.LENGTH_SHORT).show()
                //결과 화면으로 이동
                navigateToResultScreen()
                //navigateToMainScreen()
            }
        }.start()
    }

    private fun checkAnswer(answer: String) {
        val correctAnswer = "주꾸미" // 정답 설정
        if (answer == correctAnswer) {
            Toast.makeText(this, "정답입니다!", Toast.LENGTH_SHORT).show()
            // 다음 문제로 이동 또는 점수 증가
        } else {
            Toast.makeText(this, "틀렸습니다!", Toast.LENGTH_SHORT).show()
            // 오답 처리 로직
        }
    }

    private fun navigateToMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // 현재 액티비티 종료
    }

    private fun navigateToResultScreen() {
        val intent = Intent(this, ResultActivity::class.java)
        startActivity(intent)
        finish() // 현재 액티비티 종료
    }
}
