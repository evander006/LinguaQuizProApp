package com.example.languagetestapp.fragments

import android.app.AlertDialog

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.languagetestapp.R
import com.example.languagetestapp.databinding.FragmentEnglishBeginnerBinding

class EnglishBeginnerFragment : Fragment() {
    private lateinit var binding:FragmentEnglishBeginnerBinding
    private lateinit var navControl: NavController

    private val arrayQuestions= arrayOf("Как переводится \"apple\" на русский", "Что означает \"theatre\"", "Как будет \"мама\" на английском",
        "Что означает chair", "Как переводится солнце", "Что означает city","Как будет дом на английском?",
        "Что такое pen?", "Как переводится мужчина?", "Что означает water?", "Какой артикль используется перед существительными, начинающимися с гласной буквы?", "Как переводится \"Я люблю кошек\"?",
        "Как будет \"Я иду в школу\" на английском?", "Что означает \"I am running\"?", "Как будет \"Я съел яблоко\" на английском?",
        "Что такое She is reading a book?", "Как переводится \"Он не пришел\"?", "Как будет \"Я не буду плавать\" на английском?", "Что означает \"They are playing football\"?", "Как переводится \"Мы были в кино\"?",
        "Как будет \"Она будет петь\" на английском?", "Что такое You are not alone?", "Как переводится \"Мы поедем в Париж\"?", "Как будет \"Я видел этот фильм\" на английском?", "Что означает We have a dog?", "Как переводится \"Я забыл\"?",
        "Как будет \"Он не знает\" на английском?", "Что такое She can swim?", "Как переводится Мы увидимся завтра?", "Что означает milk?")

    private val arrayAnswers= arrayOf(arrayOf("Яблоко","Груша","Апельсин"), arrayOf("Музей","Опера","Театр"), arrayOf("Sister","Mother","Daughter"),
        arrayOf("Стул","Стол","Диван"), arrayOf("Moon","Sun","Star"), arrayOf("Страна","Река", "Город"), arrayOf("House","Room","The"),
        arrayOf("Карандаш","Ручка","Ластик"),  arrayOf("Girl","Boy","Man"), arrayOf("Вода","Молоко","Сок"), arrayOf("An","A","Mices"),  arrayOf("I love dogs","I love cats","I love birds"),
        arrayOf(" I go to school","I goes to school","I went to school"), arrayOf("Я плаваю","Я танцую", "Я бегу"), arrayOf("I will eat","I eat","I ate"),
        arrayOf("Она читает книгу","Она пишет книгу","Она купила книгу"), arrayOf("He doesn’t come","He didn’t come","He won’t come"), arrayOf("I don’t swim","I didn’t swim","I won’t swim"), arrayOf("Они играют в футбол","Они смотрят футбол","Они не играют в футбол"), arrayOf("We was at the cinema","We were at the cinema","We will be at the cinema"),
        arrayOf("She sings","She sang","She will sing"), arrayOf("Ты не один","Ты один","Ты не здесь"), arrayOf("We go to Paris","We went to Paris","We will go to Paris"), arrayOf("I see this movie","I saw this movie","I will see this movie"), arrayOf("У нас есть собака","У нас была собака","У нас будет собака"), arrayOf("I forget","I forgot","I will forget"),
        arrayOf("He don’t know","He doesn’t know", "He didn’t know"), arrayOf("Она плавала","Она не может плавать","Она может плавать"), arrayOf("We see tomorrow","We saw tomorrow","We will see tomorrow"), arrayOf("Чай","Молоко","Вода"))
    private val arrayIndexes=arrayOf(0,2,1,0,1,2,0,1,2,0,0,1,0,2,0,0,1,2,0,1,2,0,2,1,0,1,1,2,2,1)

    private var questionIndex=0
    private var score=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentEnglishBeginnerBinding.inflate(inflater, container, false)
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
                    navControl.navigate(R.id.action_englishBeginnerFragment_to_homeFragment)
                }
            val dialog = builder.create()
            dialog.setTitle("Вы ещё не выполнили тест \nНабрано $score из ${arrayQuestions.size}")
            dialog.show()


        }
    }

}

