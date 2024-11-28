package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text


class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        val correct = intent.getIntExtra("CORRECT",0)
        val nickname = intent.getStringExtra("NICKNAME")
        val textView = findViewById<TextView>(R.id.textView)
        val textContent = findViewById<TextView>(R.id.textContent)

        val message = when (correct) {
            in 0..3 -> "[0~3개] 아쉽네요! 🥲 다음엔 더 잘할 수 있어요! \n포기하지 말고 도전하세요!"
            in 4..7 -> "[4~7개] 좋아요! 👏 적당히 어렵게 느껴지셨나요? \n조금만 더 하면 만점도 가능할 거예요!"
            in 8..9 -> "[8~9개] 대단해요! 🎉 거의 완벽한 실력이에요! \n10점 만점까지 아~주 조금만 더!"
            10 -> "[10개] 완벽해요! 🎊 퀴즈의 왕(퀸)입니다! 👑"
            else -> "결과를 확인할 수 없습니다. 다시 도전해보세요!"
        }

        textView.text = "${nickname}님의 맞은 갯수는\n${correct}개 입니다!"


        textContent.text =  "$message"


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