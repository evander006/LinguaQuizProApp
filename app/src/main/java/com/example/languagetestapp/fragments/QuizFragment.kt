package com.example.languagetestapp.fragments

import android.app.AlertDialog
import android.content.IntentSender.OnFinished
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.example.languagetestapp.R
import com.example.languagetestapp.databinding.FragmentQuizBinding
import com.example.languagetestapp.databinding.ScoreDialogBinding

class QuizFragment : Fragment(),View.OnClickListener {
    companion object{
        var questionModelList: List<QuestionModel> = listOf()
        var timer:String=""
    }
    private lateinit var binding: FragmentQuizBinding
    private lateinit var navControl: NavController
    var currQuestionIndex=0
    var selectedAnswer=""
    var score=0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentQuizBinding.inflate(inflater, container, false)
        binding.apply {
            btn0.setOnClickListener(this@QuizFragment)
            btn1.setOnClickListener(this@QuizFragment)
            btn2.setOnClickListener(this@QuizFragment)
            btn3.setOnClickListener(this@QuizFragment)
            nextbtn.setOnClickListener(this@QuizFragment)
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadQuestions()
        navControl=Navigation.findNavController(view)
        startTimer()
    }
    private fun startTimer(){
        val milis= timer.toInt()*60*1000L
        object :CountDownTimer(milis, 1000L){
            override fun onTick(milUntilFinished: Long) {
                val sec=milUntilFinished/1000
                val min=sec/60
                val remainingsec=sec%60
                binding.textTime.text=String.format("%02d:%02d", min,remainingsec)
            }

            override fun onFinish() {
                ///finish quiz
            }

        }.start()
    }
    private fun loadQuestions(){
        if (currQuestionIndex== questionModelList.size){
            selectedAnswer=""
            finishQuiz()
            return
        }
        binding.apply {
            questionIndincator.text="Вопрос ${currQuestionIndex+1}/${questionModelList.size}"
            progressIndicator.progress=(currQuestionIndex.toFloat()/questionModelList.size.toFloat()*100).toInt()
            question.text= questionModelList[currQuestionIndex].question
            btn0.text= questionModelList[currQuestionIndex].options[0]
            btn1.text= questionModelList[currQuestionIndex].options[1]
            btn2.text= questionModelList[currQuestionIndex].options[2]
            btn3.text= questionModelList[currQuestionIndex].options[3]
        }
    }

    override fun onClick(v: View?) {
        binding.apply {
            btn0.setBackgroundColor(Color.GRAY)
            btn1.setBackgroundColor(Color.GRAY)
            btn2.setBackgroundColor(Color.GRAY)
            btn3.setBackgroundColor(Color.GRAY)
        }
        val clicked = v as Button
        if(clicked.id==R.id.nextbtn){
//            if (selectedAnswer.isEmpty()){
//                Toast.makeText(context, "Выберите один вариант ответа", Toast.LENGTH_LONG).show()
//                return;
//            }
            if (selectedAnswer==questionModelList[currQuestionIndex].correct){
                score++
                Log.i("Score of quiz", score.toString())
            }
            currQuestionIndex++
            loadQuestions()
        }else{
            selectedAnswer=clicked.text.toString()
            clicked.setBackgroundColor(Color.BLUE)
        }
    }
    private fun finishQuiz(){
        val total = questionModelList.size
        val perc=(score.toFloat()/total.toFloat()*100).toInt()
        val dialogBinding=ScoreDialogBinding.inflate(layoutInflater)
        dialogBinding.apply {
            progressIndicator.progress=perc
            progressPercentageText.text="$perc%"
            if (perc>60){
                scoreTitle.text="Поздравляю! Это очень хороший результат!"
            }else{
                scoreTitle.text="Неплохо! Но есть над чем работать"
            }
            questionsPassSubt.text="$score из ${questionModelList.size} правильных"
            finishBtn.setOnClickListener {
                navControl.navigate(R.id.action_quizFragment_to_homeFragment)
            }
        }
        AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .setCancelable(true)
            .show()

    }

}