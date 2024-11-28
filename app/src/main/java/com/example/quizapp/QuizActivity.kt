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
    private lateinit var option1Text: TextView
    private lateinit var option2Text: TextView

    private var timeLeft: Int = 10 // 타이머 시간 (초)
    private var progress: Int = 0
    private var currentQuestionIndex = 0 // 현재 문제 인덱스

    private val questions = listOf(
        Question("바른 표현은?", listOf("주꾸미", "쭈꾸미"), "주꾸미"),
        Question("애플의 한국어 뜻은?", listOf("사과", "바나나"), "사과"),
        Question("한국의 수도는?", listOf("서울", "부산"), "서울"),
        Question("1 + 1 = ?", listOf("2", "11"), "2"),
        Question("가장 큰 행성은?", listOf("지구", "목성"), "목성"),
        Question("코틀린은 어떤 언어?", listOf("프로그래밍 언어", "음식"), "프로그래밍 언어"),
        Question("파이썬은?", listOf("동물", "프로그래밍 언어"), "프로그래밍 언어"),
        Question("HTML은?", listOf("마크업 언어", "음식"), "마크업 언어"),
        Question("안드로이드는?", listOf("운영체제", "과일"), "운영체제"),
        Question("자바는?", listOf("섬", "프로그래밍 언어"), "프로그래밍 언어")
    )

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

        // FrameLayout 내부 TextView 참조
        option1Text = option1.findViewById(R.id.textview1)
        option2Text = option2.findViewById(R.id.textview2) // 대소문자 수정

        // 문제 표시
        showQuestion()

        // 선택지 클릭 이벤트
        option1.setOnClickListener { checkAnswer(0) }
        option2.setOnClickListener { checkAnswer(1) }
    }

    private fun showQuestion() {
        if (currentQuestionIndex < questions.size) {
            val currentQuestion = questions[currentQuestionIndex]
            findViewById<TextView>(R.id.textView2).text = currentQuestion.questionText
            option1Text.text = currentQuestion.options[0]
            option2Text.text = currentQuestion.options[1]
            startTimer()
        } else {
            Toast.makeText(this, "퀴즈 완료!", Toast.LENGTH_SHORT).show()
            navigateToMainScreen()
        }
    }

    private var countDownTimer: CountDownTimer? = null

    private fun startTimer() {
        timeLeft = 10
        progress = 0
        progressBar.progress = progress

        countDownTimer?.cancel() // 이전 타이머 취소
        countDownTimer = object : CountDownTimer(timeLeft * 1000L, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft--
                timerTextView.text = "남은 시간 : ${timeLeft}초"
                progress += 10
                progressBar.progress = progress
            }

            override fun onFinish() {
                Toast.makeText(this@QuizActivity, "시간 초과!", Toast.LENGTH_SHORT).show()
                currentQuestionIndex++
                showQuestion()
                //navigateToResultScreen()
            }
        }.start()
    }

    private fun checkAnswer(selectedIndex: Int) {
        val currentQuestion = questions[currentQuestionIndex]
        val selectedAnswer = currentQuestion.options[selectedIndex]

        if (selectedAnswer == currentQuestion.correctAnswer) {
            Toast.makeText(this, "정답입니다!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "틀렸습니다!", Toast.LENGTH_SHORT).show()
        }

        currentQuestionIndex++
        showQuestion()
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
