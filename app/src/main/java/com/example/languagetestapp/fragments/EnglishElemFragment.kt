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
import com.example.languagetestapp.databinding.FragmentEnglishElemBinding

class EnglishElemFragment : Fragment() {
    private lateinit var navControl: NavController
    private lateinit var binding:FragmentEnglishElemBinding
    private val arrayQuestions= arrayOf("Cиноним для \"big\"", "\"Cat\"", "Какое слово является глаголом: run, table или blue?",
        "Какое слово является существительным: jump, pencil или sing?", "\"Happy\"", "Антоним для \"hot\"","Заполните пропуск: I _______ to school every day",
        "\"Child\" во множиственном числе", "Заполните пропуск: She _______ books every day", "Pencil", "\"Mouse\" во ножественном числе", "Антоним для \"slow\"",
        "Синоним для \"beautiful\"", "Какое слово является глаголом в настоящем времени: swim, swam или swum?", "\"Goose\" во ножественном числе",
        "Заполните пропуск: We _______ a movie last night.", "The girl is playing with her _____", "The sky is _____", "Переведите \"bookshelf\"", "I _____ (go) to the store yesterday.",
        "She _____ every morning.", "_____ is a doctor.", "The gift is for _____", "I like _____ (eat) sweets every day", "The book is _____", "Tracksuit",
        "Country", "My name ____ John", "We ____ a happy family", "_____ color is blue.")

    private val arrayAnswers= arrayOf(arrayOf("small","large","slow"), arrayOf("Собака","Корова","Кот"), arrayOf("run","table","blue"),
        arrayOf("pencil","jump","sing"), arrayOf("Счастливый","Грустный","Быстрый"), arrayOf("Delicious","Make", "Cold"), arrayOf("come","go","wear"),
        arrayOf("Childs","Children","Childrens"),  arrayOf("reads","read","reading"), arrayOf("Макет","Карандаш","Мышка"), arrayOf("Mice","Mouses","Mices"),  arrayOf("Big","Fast","Calm"),
        arrayOf("Gorgeous","Scary","Lovely"), arrayOf("Swam","Swum", "Swim"), arrayOf("Geese","Gooses","Geeses"),
        arrayOf("See","Saw","Seen"), arrayOf("doll","book","coat"), arrayOf("red","cloudy","rapid"), arrayOf("Книжная полка","Шкаф","Книга"), arrayOf("Gone","Gont","Went"),
        arrayOf("runs","falls","puts"), arrayOf("They","He","I"), arrayOf("her","she","we"), arrayOf("bringing","seeing","eating"), arrayOf("mine","he","they"), arrayOf("Стол","Кофта","Спортивный костюм"),
        arrayOf("Компания","Деньги", "Страна"), arrayOf("am","are","is"), arrayOf("are","is","am"), arrayOf("-","The","An"))
    private val arrayIndexes=arrayOf(1,2,0,0,0,2,1,1,0,1,0,1,0,2,0,1,0,1,0,2,0,1,0,2,0,2,2,2,0,1,0)

    private var questionIndex=0
    private var score=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentEnglishElemBinding.inflate(inflater, container, false)
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
        navControl=Navigation.findNavController(view)
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
                navControl.navigate(R.id.action_englishBeginnerFragment_to_homeFragment)
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