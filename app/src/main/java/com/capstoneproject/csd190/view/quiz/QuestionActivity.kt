package com.capstoneproject.csd190.view.quiz

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.capstoneproject.csd190.R
import com.capstoneproject.csd190.model.Question
import com.capstoneproject.csd190.utils.QuizData
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity() {
    private var currentPosition: Int = 1
    private var selectedOption: Int = 0
    private var questionList: ArrayList<Question>? = null
    private var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        questionList = QuizData.getQuestion()
        setQuestion()

        tv_option1.setOnClickListener {
            selectedAnswerStyle(tv_option1, 1)
        }

        tv_option2.setOnClickListener {
            selectedAnswerStyle(tv_option2, 2)
        }

        tv_option3.setOnClickListener {
            selectedAnswerStyle(tv_option3, 3)
        }

        tv_option4.setOnClickListener {
            selectedAnswerStyle(tv_option4, 4)
        }

        submit.setOnClickListener {
            if (selectedOption != 0) {
                val question = questionList!![currentPosition - 1]

                if (selectedOption != question.answer) {
                    setColor(selectedOption, R.drawable.custom_question_option_wrong)
                } else {
                    score++
                }

                setColor(question.answer, R.drawable.custom_question_option_correct)

                if (currentPosition == questionList!!.size)
                    submit.text = getString(R.string.btn_submit)
                else
                    submit.text = getString(R.string.next)
            } else {
                currentPosition++

                when {
                    currentPosition <= questionList!!.size -> {
                        setQuestion()
                    }
                    else -> {
                        val intent = Intent(this, ResultQuizActivity::class.java)
                        intent.putExtra(QuizData.score, score.toString())
                        intent.putExtra("total quiz", questionList!!.size.toString())
                        startActivity(intent)
                        finish()
                    }
                }
            }

            selectedOption = 0
        }
    }

    private fun setColor(opt: Int, color: Int) {
        when (opt) {
            1 -> {
                tv_option1.background = ContextCompat.getDrawable(this, color)
            }
            2 -> {
                tv_option2.background = ContextCompat.getDrawable(this, color)
            }
            3 -> {
                tv_option3.background = ContextCompat.getDrawable(this, color)
            }
            4 -> {
                tv_option4.background = ContextCompat.getDrawable(this, color)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setQuestion() {
        val ques = questionList!![currentPosition - 1]
        answerStyle()

        progressBar.progress = currentPosition
        progressBar.max = questionList!!.size
        tv_progress_question.text = "$currentPosition" + " of " + "${questionList!!.size}"

        tv_question.text = ques.question
        tv_option1.text = ques.option1
        tv_option2.text = ques.option2
        tv_option3.text = ques.option3
        tv_option4.text = ques.option4
    }

    private fun answerStyle() {
        val optionList: ArrayList<TextView> = arrayListOf()
        optionList.add(0, tv_option1)
        optionList.add(1, tv_option2)
        optionList.add(2, tv_option3)
        optionList.add(3, tv_option4)

        for (i in optionList) {
            i.setTextColor(Color.parseColor("#555151"))
            i.background = ContextCompat.getDrawable(this, R.drawable.custom_question_option)
            i.typeface = Typeface.DEFAULT
        }
    }

    private fun selectedAnswerStyle(view: TextView, opt: Int) {
        answerStyle()

        selectedOption = opt

        view.background =
            ContextCompat.getDrawable(this, R.drawable.custom_question_option_selected)
        view.typeface = Typeface.DEFAULT_BOLD

        view.setTextColor(Color.parseColor("#000000"))
    }
}