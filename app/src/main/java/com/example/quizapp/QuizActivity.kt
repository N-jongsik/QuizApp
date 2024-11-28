package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var timerTextView: TextView
    private lateinit var option1: RelativeLayout
    private lateinit var option2: RelativeLayout
    private lateinit var option1Text: TextView
    private lateinit var option2Text: TextView

    private var timeLeft: Int = 5 // 타이머 시간 (초)
    private var progress: Int = 0
    private var currentQuestionIndex = 0 // 현재 문제 인덱스
    private var cnt : Int = 0
    private var nickname : String? =""

    private val questions = listOf(
        Question("바른 표현은?", listOf("주꾸미", "쭈꾸미"), "주꾸미","문어과의 연체동물 중 하나 표준어는 주꾸미 입니다!"),
        Question("군대의 게급 순서로 맞는 것은?", listOf("이병-일병-상병", "일병-이병-삼병"), "이병-일병-상병","군대의 계급은 이병-일병-상병 순서입니다!"),
        Question("바른 표현은?", listOf("메세지", "메시지"), "메시지","외래어 표기법에 따라 메시지로 표기!"),
        Question("물티슈의 영어표기로 알맞는 것은?", listOf("water tissue", "wet tissue"), "wet tissue","물티슈의 영어표기 water tissue가 아닙니다!"),
        Question("바른 표현은?", listOf("눈곱", "눈꼽"), "눈곱","눈과 곱이 합해진 말 발음은 눈꼽입니다!"),
        Question("바른 표현은?", listOf("곁땀", "겨땀"), "곁땀","겨드랑이에서 나는 땀을 뜻하는 표준어는 곁땀!"),
        Question("호주의 수도는?", listOf("캔버라", "시드니"), "캔버라","호주의 수도는 캔버라 입니다!"),
        Question("바른 표현은?", listOf("결제 서류", "결재 서류"), "결재 서류","결재 : 안건을 허가하거나 승인!"),
        Question("순결 변함없는 사랑의 꽃말을 가진 꽃은?", listOf("해바라기", "백합"), "백합","백합의 꽃말은 변함없는 사랑입니다!"),
        Question("바른 표현은?", listOf("내일 봬요", "내일 뵈요"), "내일 봬요","뵈어요의 줄임말 봬요 (뵈 = 하, 봬 = 해 로 바꿔 써서 확인하면 쉬워요!)")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        nickname = intent.getStringExtra("NICKNAME")
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
            navigateToResultScreen()
        }
    }

    private var countDownTimer: CountDownTimer? = null

    private fun startTimer() {
        timeLeft = 5
        progress = 0
        progressBar.progress = progress

        countDownTimer?.cancel() // 이전 타이머 취소
        countDownTimer = object : CountDownTimer(timeLeft * 1000L, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft--
                timerTextView.text = "남은 시간 : ${timeLeft}초"
                progress += 20
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
            cnt++
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
        intent.putExtra("CORRECT",cnt)
        intent.putExtra("NICKNAME",nickname)
        startActivity(intent)
        finish() // 현재 액티비티 종료
    }
}
