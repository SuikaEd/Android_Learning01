package com.example.suikaex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"

class MainActivity : AppCompatActivity() {

    //惰性初始化QuizViewModel
    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView: TextView

    //增加Question对象集合
    //移除
    /*
    private val questionBank = listOf(
        Question(R.string.question_01, true),
        Question(R.string.question_02, true),
        Question(R.string.question_03, true),
        Question(R.string.question_04, true),
        Question(R.string.question_05, true)
    )

    private var currentIndex = 0

     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle? called")
        setContentView(R.layout.activity_main)

        //访问ViewModel
        //删除
        /*
        val provider: ViewModelProvider = ViewModelProviders.of(this)
        val quizViewModel = provider.get(QuizViewModel::class.java)
        Log.d(TAG, "Got a QuizViewModel: $quizViewModel")

         */

        //通过资源ID访问视图对象
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)

        //让nextButton在开始时不可用
        if(trueButton.isEnabled)
        {
            nextButton.isEnabled = false
        }

        //为TRUE按钮设置监听器
        trueButton.setOnClickListener { view: View ->
            /*
            Toast.makeText(
                this,
                R.string.correct_toast,
                Toast.LENGTH_SHORT)
                .show()

             */
            checkAnswer(true)
            buttonUseful(true)
        }

        //为FALSE按钮设置监听器
        falseButton.setOnClickListener { view: View ->

            /*Toast.makeText(
                this,
                R.string.incorrect_toast,
                Toast.LENGTH_SHORT)
                .show()

             */
            checkAnswer(false)
            buttonUseful(true)
        }

        //使用next按钮
        nextButton.setOnClickListener{
            //currentIndex = (currentIndex + 1) % questionBank.size
            /*
            val questionTextResId = questionBank[currentIndex].textResId
            questionTextView.setText(questionTextResId)
             */
            quizViewModel.moveToNext()
            updateQuestion()
            buttonUseful(false)
        }

        /*
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
         */
        updateQuestion()
    }

    //覆盖onSaveInstanceState函数
    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG, "onSaveInstanceState")
        savedInstanceState.putInt(KEY_INDEX, quizViewModel.currentIndex)
    }

    //覆盖生命周期函数
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    //使用updateQuestion（）封装公共代码
    private fun updateQuestion() {
        //Log.d(TAG, "Updating question text", Exception())
        //val questionTextResId = questionBank[currentIndex].textResId
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
    }

    //增加checkAnswer（Boolean）函数
    private fun checkAnswer(userAnswer: Boolean) {
        //val correctAnswer = questionBank[currentIndex].answer
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
            .show()
    }

    //true和false按钮点击后，true&false不可用且next可用
    private fun buttonUseful(buttonCondition: Boolean) {
        if (buttonCondition) {
            trueButton.isEnabled=false
            falseButton.isEnabled=false
            nextButton.isEnabled=true
        } else{
            trueButton.isEnabled=true
            falseButton.isEnabled=true
            nextButton.isEnabled=false
        }
    }
}