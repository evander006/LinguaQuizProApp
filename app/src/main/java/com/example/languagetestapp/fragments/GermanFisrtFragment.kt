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
import com.example.languagetestapp.databinding.FragmentGermanFisrtBinding

class GermanFisrtFragment : Fragment() {
    private lateinit var binding: FragmentGermanFisrtBinding
    private lateinit var navControl: NavController

    private val arrayQuestions= arrayOf("Wie heißt du?", "Wie geht es dir?",
        "Was ist dein Beruf?", "Wo wohnst du?", "Wie alt bist du?", "Hast du Geschwister?",
        "Was ist das?", "Wie spät ist es?", "Was machst du gern in deiner Freizeit?",
        "Wie ist das Wetter heute?", "Was ist dein Lieblingsessen?", "Wie viel kostet das Brot?", "Wo ist die Toilette?",
        "Wie komme ich zum Bahnhof?", "Kannst du mir helfen?", "Wie viele Äpfel hast du?", "Was trinkst du gern?", "Wann stehst du auf?",
        "Wo arbeitest du?", "Was ist dein Lieblingsfilm?", "Wie lange lernst du schon Deutsch?", "Was hast du gestern gemacht?",
        "Was machst du morgen?", "Was ist dein Lieblingsfach in der Schule?", "Wie oft gehst du ins Fitnessstudio?", "Was isst du zum Frühstück?",
        "Wie kommst du zur Arbeit?", "Was machst du am Wochenende?", "Was liest du gerade?", "Was siehst du?")


    private val arrayAnswers= arrayOf(arrayOf("Ich habe Maria.", "Ich Maria.", "Ich bin Maria."),
        arrayOf("Ich bin in Berlin.", "Ich bin 20 Jahre alt.", "Ich bin gut, danke."),
        arrayOf("Ich Lehrer.", "Ich bin Lehrer.", "Ich habe Lehrer."),
        arrayOf("Ich in Berlin.", "Ich wohne in Berlin.", "Ich habe in Berlin."),
        arrayOf("Ich 20 Jahre alt.", "Ich bin 20 Jahre alt.", "Ich habe 20 Jahre alt."),
        arrayOf("Ja, ich einen Bruder.", "Ja, ich habe einen Bruder.", "Ja, ich bin einen Bruder."),
        arrayOf("Das ein Buch.", "Das ist ein Buch.", "Das habe ein Buch."),
        arrayOf("Es zwei Uhr.", "Es ist zwei Uhr.", "Es habe zwei Uhr."),
        arrayOf("Ich gern Bücher.", "Ich lese gern Bücher.", "Ich habe gern Bücher."),
        arrayOf("Es sonnig.", "Es ist sonnig.", "Es habe sonnig."),
        arrayOf("Mein Pizza.", "Mein habe Pizza.", "Mein Lieblingsessen ist Pizza."),
        arrayOf("Das Brot zwei Euro.", "Das Brot kostet zwei Euro.", "Das Brot habe zwei Euro."),
        arrayOf("Die Toilette dort drüben.", "Die Toilette habe dort drüben.", "Die Toilette ist dort drüben."),
        arrayOf("Geradeaus und dann links.", "Geh geradeaus und dann links.", "Habe geradeaus und dann links."),
        arrayOf("Ja, ich dir helfen.", "Ja, ich kann dir helfen.", "Ja, ich habe dir helfen."),
        arrayOf("Ich drei Äpfel.", "Ich bin drei Äpfel.", "Ich habe drei Äpfel."),
        arrayOf("Ich gern Kaffee.", "Ich trinke gern Kaffee.", "Ich habe gern Kaffee."),
        arrayOf("Ich um sieben Uhr auf.", "Ich stehe um sieben Uhr auf.", "Ich habe um sieben Uhr auf."),
        arrayOf("Ich bei Siemens.", "Ich arbeite bei Siemens.", "Ich habe bei Siemens."),
        arrayOf("Mein Titanic.", "Mein habe Titanic.", "Mein Lieblingsfilm ist Titanic."),
        arrayOf("Ich seit einem Jahr Deutsch.", "Ich lerne seit einem Jahr Deutsch.", "Ich habe seit einem Jahr Deutsch."),
        arrayOf("Ich einen Film gesehen.", "Ich bin einen Film gesehen.", "Ich habe einen Film gesehen."),
        arrayOf("Ich ins Kino.", "Ich gehe ins Kino.", "Ich habe ins Kino."),
        arrayOf("Mein Mathematik.", "Mein habe Mathematik.", "Mein Lieblingsfach ist Mathematik."),
        arrayOf("Ich zweimal pro Woche ins Fitnessstudio.", "Ich gehe zweimal pro Woche ins Fitnessstudio.", "Ich habe zweimal pro Woche ins Fitnessstudio."),
        arrayOf("Ich Müsli zum Frühstück.", "Ich esse Müsli zum Frühstück.", "Ich habe Müsli zum Frühstück."),
        arrayOf("Ich mit dem Fahrrad zur Arbeit.", "Ich fahre mit dem Fahrrad zur Arbeit.", "Ich habe mit dem Fahrrad zur Arbeit."),
        arrayOf("Ich mich mit Freunden.", "Ich treffe mich mit Freunden.", "Ich habe mich mit Freunden."),
        arrayOf("Ich gerade Harry Potter.", "Ich lese gerade Harry Potter.", "Ich habe gerade Harry Potter."),
        arrayOf("Ich einen Hund.", "Ich sehe einen Hund.", "Ich habe einen Hund."))

    private val arrayIndexes=arrayOf(2,2,1,2,1,2,1,1,1,1,2,1,1,1,1,2,1,1,1,2,1,2,1,2,1,1,1,1,1,1)
    private var questionIndex=0
    private var score=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentGermanFisrtBinding.inflate(inflater, container, false)
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
                    navControl.navigate(R.id.action_germanFisrtFragment_to_homeFragment)
                }
            val dialog = builder.create()
            dialog.setTitle("Вы ещё не выполнили тест \nНабрано $score из ${arrayQuestions.size}")
            dialog.show()
        }
    }

}