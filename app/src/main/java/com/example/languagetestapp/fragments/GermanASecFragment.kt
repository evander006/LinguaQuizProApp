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
import com.example.languagetestapp.databinding.FragmentGermanASecBinding
import com.example.languagetestapp.databinding.FragmentGermanFisrtBinding

class GermanASecFragment : Fragment() {
    private lateinit var binding: FragmentGermanASecBinding
    private lateinit var navControl: NavController
    private val arrayQuestions= arrayOf("Was machst du in deiner Freizeit?",
        "Was hast du gestern Abend gemacht?",
        "Was wirst du morgen machen?",
        "Wie lange lernst du schon Deutsch?",
        "Was ist dein Lieblingsessen?",
        "Was ist dein Lieblingsfilm?",
        "Was ist dein Lieblingsbuch?",
        "Was ist dein Lieblingssport?",
        "Was ist dein Lieblingslied?",
        "Was ist dein Lieblingsland?",
        "Was ist deine Lieblingsstadt?",
        "Was ist dein Lieblingsfach in der Schule?",
        "Was ist dein Lieblingshobby?",
        "Was ist dein Lieblingswort auf Deutsch?",
        "Was ist dein Lieblingszitat?",
        "Was ist dein Lieblingsbuch?",
        "Was ist dein Lieblingsgemüse?",
        "Was ist dein Lieblingsobst?",
        "Was ist dein Lieblingskleidungsstück?",
        "Was ist dein Lieblingsauto?",
        "Wie komme ich zum Bahnhof?",
        "Wo ist die nächste Apotheke?",
        "Wie weit ist der Supermarkt?",
        "Ich habe ____ Bruder.",
        "Я счастлив(а)", "Schmetterling",
        "Спасибо на немецком", "Entschuldigung",
        "Доброе утро auf Deutsch", "Ich liebe dich")

    private val arrayAnswers= arrayOf(arrayOf("Ich spiele Fußball.", "Ich spiele Fußballen.", "Ich Fußball spiele."),
        arrayOf("Ich habe einen Film gesehen.", "Ich einen Film gesehen habe.", "Ich gesehen einen Film habe."),
        arrayOf("Ich gehen ins Kino werde.", "Ich ins Kino gehen werde.", "Ich werde ins Kino gehen."),
        arrayOf("Ich Deutsch lerne seit zwei Jahren.", "Ich seit zwei Jahren Deutsch lerne.", "Ich lerne seit zwei Jahren Deutsch."),
        arrayOf("Mein habe Pizza.", "Mein Lieblingsessen ist Pizza.", "Mein Pizza."),
        arrayOf("Mein Titanic.", "Mein habe Titanic.", "Mein Lieblingsfilm ist Titanic."),
        arrayOf("Mein Lieblingsbuch ist \"Der Alchimist\".", "Mein habe \"Der Alchimist\".", "Mein \"Der Alchimist\"."),
        arrayOf("Mein Lieblingssport ist Fußball.", "Mein habe Fußball.", "Mein Fußball."),
        arrayOf("Mein “Imagine”.", "Mein Lieblingslied ist “Imagine”.", "Mein habe “Imagine”."),
        arrayOf("Mein Deutschland.", "Mein habe Deutschland.", "Mein Lieblingsland ist Deutschland."),
        arrayOf("Meine Lieblingsstadt ist Berlin.", "Meine habe Berlin.", "Meine Berlin."),
        arrayOf("Mein Pizza.", "Mein habe Pizza.", "Mein Lieblingsessen ist Pizza."),
        arrayOf("Mein Lieblingsfach ist Mathematik.", "Mein Mathematik.", "Mein habe Mathematik."),
        arrayOf("Mein habe Lesen.", "Mein Lieblingshobby ist Lesen.", "Mein Lesen."),
        arrayOf("Ich “Sei du selbst; alle anderen sind bereits vergeben” mag.", "Ich mag “Sei du selbst; alle anderen sind bereits vergeben”.", "Ich bin “Sei du selbst; alle anderen sind bereits vergeben”."),
        arrayOf("Ich liebe “Der Alchimist”.", "Ich “Der Alchimist” liebe.", "Ich lieben “Der Alchimist”."),
        arrayOf("Ich liebe Brokkoli.", "Ich Brokkoli liebst.", "Ich lieben Brokkoli."),
        arrayOf("Ich Äpfel liebe.", "Ich lieben Äpfel.", "Ich liebe Apfel."),
        arrayOf("Ich lieben Jeans.", "Ich Jeans liebe.", "Ich liebe Jeans."),
        arrayOf("Ich liebe Tesla.", "Ich Tesla liebe.", "Ich Tesla geradeuas liebe."),
        arrayOf("Geh geradeaus und dann links.", "Geh geradeaus und dann rechts.", "Geh geradeaus und dann geradeaus."),
        arrayOf("Die Apotheke ist um die Ecke.", "Die Apotheke ist auf dem Dach", "Die Apotheke ist unter dem Tisch."),
        arrayOf("Der Supermarkt ist 10 Tage zu Fuß entfernt.", "Der Supermarkt ist 10 Stunden zu Fuß entfernt", "Der Supermarkt ist 10 Minuten zu Fuß entfernt."),
        arrayOf("ein", "einen", "eine"),
        arrayOf("Ich bin glücklich.", "Ich bin traurig.", "Ich bin wütend."),
        arrayOf("Butterfly", "Caterpillar", "Bee"),
        arrayOf("Bitte", "Danke", "Hallo"),
        arrayOf("Извините", "Пока", "Пожалуйста"),
        arrayOf("Guten Morgen", "Gute Nacht", "Guten Tag"),
        arrayOf("Люблю её", "Ты моя любовь", "Я люблю тебя"))

    private val arrayIndexes=arrayOf(0,0,2,2,1,2,0,0,1,2,0,2,0,1,1,0,0,2, 2, 0, 0, 0, 2, 1, 0, 0, 1, 0, 0, 2)
    private var questionIndex=0
    private var score=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentGermanASecBinding.inflate(inflater, container, false)
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
                navControl.navigate(R.id.action_germanASecFragment_to_homeFragment)
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