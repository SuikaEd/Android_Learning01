package com.example.suikaex01

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {
    //移除
    /*
    init {
        Log.d(TAG, "ViewModel instance created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel instance about to be destroyed")
    }

     */

    //移动
    var currentIndex = 0
    private val questionBank = listOf(
        Question(R.string.question_01, true),
        Question(R.string.question_02, true),
        Question(R.string.question_03, true),
        Question(R.string.question_04, true),
        Question(R.string.question_05, true)
    )

    //添加业务逻辑
    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer
    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }
}