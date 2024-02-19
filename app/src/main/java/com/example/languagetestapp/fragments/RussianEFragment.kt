package com.example.languagetestapp.fragments

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.languagetestapp.R
import com.example.languagetestapp.databinding.FragmentEnglishBeginnerBinding
import com.example.languagetestapp.databinding.FragmentRussianEBinding

class RussianEFragment : Fragment() {
    private lateinit var binding: FragmentRussianEBinding
    private lateinit var navControl: NavController

    private val arrayQuestions= arrayOf("ч_gрный","больш_й","ш_колад","стаж_р","раскоч_вать","затуш_вать","ш_лк","сгущ_нка","сильная изж_га","в подж_ге","икч_мный","ож_г палец","ч_рнеть","дириж_р","леч_","маж_р","Творительный падеж: багаж_м","Прилагательное от биржа",
        "истощ_нный","ч_каться","щ_голь","ш_умен","прож_рливый","прич_ска","реч_нка","лиш_н","медвеж_нок","ж_нглёр","кош_лка","розовощ_кий")

    private val arrayAnswers= arrayOf(arrayOf("О","Ё","Е"),arrayOf("О","Ё","Е"),arrayOf("О","Ё","Е"),arrayOf("О","Ё","Е"),arrayOf("О","Ё","Е"),arrayOf("О","Ё","Е"),arrayOf("О","Ё","Е"),
        arrayOf("О","Ё","Е"),arrayOf("О","Ё","Е"),arrayOf("О","Ё","Е"),arrayOf("О","Ё","Е"),arrayOf("О","Ё","Е"),arrayOf("О","Ё","Е"),arrayOf("О","Ё","Е"),arrayOf("О","Ё","Е"),arrayOf("О","Ё","Е"),
        arrayOf("О","Ё","Е"),arrayOf("О","Ё","Е"),arrayOf("О","Ё","Е"),arrayOf("О","Ё","Е"),arrayOf("О","Ё","Е"),arrayOf("О","Ё","Е"),arrayOf("О","Ё","Е"),arrayOf("О","Ё","Е"),arrayOf("О","Ё","Е"),arrayOf("О","Ё","Е"),
        arrayOf("О","Ё","Е"),arrayOf("О","Ё","Е"),arrayOf("О","Ё","Е"),arrayOf("О","Ё","Е"))
    private val arrayIndexes=arrayOf(1,0,0,1,2,2,1,1,0,0,1,1,2,1,0,0,0,2,1,0,1,0,0,1,0,2,0,0,2,1)

    private var questionIndex=0
    private var score=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentRussianEBinding.inflate(inflater, container,false)
        binding.option1.setOnClickListener{
            check(0)
        }
        binding.option2.setOnClickListener{
            check(1)
        }
        binding.option3.setOnClickListener{
            check(2)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        returnButton()
        displayQuestion()
        var buttonindex=null
        buttonindex?.let { correctButtonColor(it) }
        buttonindex?.let { wrongButtonColor(it) }
        resetButtonColor()
        var selectedAnswerIndex=null
        selectedAnswerIndex?.let { check(it) }

    }

    private fun init(view: View){
        navControl= Navigation.findNavController(view)
    }


    private fun correctButtonColor(buttonindex: Int){
        when(buttonindex){
            0 -> binding.option1.setBackgroundColor(Color.GREEN)
            1 -> binding.option2.setBackgroundColor(Color.GREEN)
            2 -> binding.option3.setBackgroundColor(Color.GREEN)
        }
    }

    private fun wrongButtonColor(buttonindex: Int){
        when(buttonindex){
            0 -> binding.option1.setBackgroundColor(Color.RED)
            1 -> binding.option2.setBackgroundColor(Color.RED)
            2 -> binding.option3.setBackgroundColor(Color.RED)
        }
    }

    private fun resetButtonColor(){
        binding.option1.setBackgroundColor(Color.YELLOW)
        binding.option2.setBackgroundColor(Color.YELLOW)
        binding.option3.setBackgroundColor(Color.YELLOW)
    }

    private fun showResults(){
        val builderone = AlertDialog.Builder(context)
        builderone.setMessage("Хотите пройти тест заново?")
            .setCancelable(false)
            .setPositiveButton("Да") { dialogInterface, i ->
                restartFragment()
            }
            .setNegativeButton("Нет") { dialogInterface, i ->
                navControl.navigate(R.id.action_russianEFragment_to_homeFragment)
            }
        val dialog = builderone.create()
        dialog.setTitle("Вы набрали: $score из ${arrayQuestions.size}")
        dialog.show()
    }

    private fun displayQuestion(){
        binding.questions.text=arrayQuestions[questionIndex]
        binding.option1.text=arrayAnswers[questionIndex][0]
        binding.option2.text=arrayAnswers[questionIndex][1]
        binding.option3.text=arrayAnswers[questionIndex][2]
        resetButtonColor()
    }

    private fun check(selectedAnswerIndex: Int){
        val correctAnswerIndex=arrayIndexes[questionIndex]
        if (selectedAnswerIndex==correctAnswerIndex){
            score++
            correctButtonColor(selectedAnswerIndex)
        } else{
            wrongButtonColor(selectedAnswerIndex)
            correctButtonColor(correctAnswerIndex)
        }
        if (questionIndex < arrayQuestions.size-1){
            questionIndex++
            binding.questions.postDelayed({displayQuestion()}, 1000)
        }else{
            showResults()
            binding.option1.isEnabled=false
            binding.option2.isEnabled=false
            binding.option3.isEnabled=false
        }
    }
    fun restartFragment(){
        questionIndex=0
        score=0
        displayQuestion()
        var buttonindex=null
        buttonindex?.let { correctButtonColor(it) }
        buttonindex?.let { wrongButtonColor(it) }
        resetButtonColor()
        var selectedAnswerIndex=null
        selectedAnswerIndex?.let { check(it) }
        binding.option1.isEnabled=true
        binding.option2.isEnabled=true
        binding.option3.isEnabled=true

    }
    private fun returnButton(){
        binding.back.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setMessage("Хотите продолжить?")
                .setCancelable(true)
                .setPositiveButton("Да") { dialogInterface, i ->
                }
                .setNegativeButton("Нет") { dialogInterface, i ->
                    navControl.navigate(R.id.action_englishElemFragment_to_homeFragment)
                }
            val dialog = builder.create()
            dialog.setTitle("Вы ещё не выполнили тест \nНабрано $score из ${arrayQuestions.size}")
            dialog.show()
        }
    }


}