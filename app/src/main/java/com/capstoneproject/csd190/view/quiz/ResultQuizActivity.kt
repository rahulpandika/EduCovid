package com.capstoneproject.csd190.view.quiz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.capstoneproject.csd190.MainActivity
import com.capstoneproject.csd190.R
import com.capstoneproject.csd190.utils.QuizData
import kotlinx.android.synthetic.main.activity_result_quiz.*

class ResultQuizActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_quiz)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        val score = intent.getStringExtra(QuizData.score)
        val totalQuestion = intent.getStringExtra("total quiz")

        tv_score.text = "${score}/${totalQuestion}"

        btn_selesai.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        btn_ulang.setOnClickListener {
            startActivity(Intent(this, QuestionActivity::class.java))
            finish()
        }
    }
}