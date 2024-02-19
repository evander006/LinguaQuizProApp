package com.example.languagetestapp.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.languagetestapp.R
import com.example.languagetestapp.databinding.FragmentHomeBinding
import com.example.languagetestapp.databinding.FragmentSignupBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var navControl: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        regulate()
    }
    private fun init(view: View){
        navControl= Navigation.findNavController(view)
    }

    private fun regulate(){
        var isButton1Clicked = false

        binding.engbtn.setOnClickListener {
            isButton1Clicked = !isButton1Clicked
            binding.engbtn.setBackgroundColor(if (!isButton1Clicked) Color.BLACK else Color.WHITE)
            binding.engbtn.setTextColor(if (!isButton1Clicked) Color.WHITE else Color.BLACK)
            binding.textEng1.isVisible=true
            binding.textEng2.isVisible=true
            binding.horizontalScrollView.isVisible=true
            binding.horizontalScrollView2.isVisible=if (!isButton1Clicked) true else false
            binding.horizontalScrollView3.isVisible=if (!isButton1Clicked) true else false
            binding.textGerman1.isVisible=if (!isButton1Clicked) true else false
            binding.textGerman2.isVisible=if (!isButton1Clicked) true else false
            binding.textRus1.isVisible=if (!isButton1Clicked) true else false
            binding.textRus2.isVisible=if (!isButton1Clicked) true else false

        }
        binding.germanbtn.setOnClickListener {
            isButton1Clicked = !isButton1Clicked
            binding.germanbtn.setBackgroundColor(if (!isButton1Clicked) Color.BLACK else Color.WHITE)
            binding.germanbtn.setTextColor(if (!isButton1Clicked) Color.WHITE else Color.BLACK)
            binding.textGerman1.isVisible=true
            binding.textGerman2.isVisible=true
            binding.horizontalScrollView2.isVisible=true
            binding.horizontalScrollView.isVisible=if (!isButton1Clicked) true else false
            binding.horizontalScrollView3.isVisible=if (!isButton1Clicked) true else false
            binding.textEng1.isVisible=if (!isButton1Clicked) true else false
            binding.textEng2.isVisible=if (!isButton1Clicked) true else false
            binding.textRus1.isVisible=if (!isButton1Clicked) true else false
            binding.textRus2.isVisible=if (!isButton1Clicked) true else false

        }
        binding.russianbtn.setOnClickListener {
            isButton1Clicked = !isButton1Clicked
            binding.russianbtn.setBackgroundColor(if (!isButton1Clicked) Color.BLACK else Color.WHITE)
            binding.russianbtn.setTextColor(if (!isButton1Clicked) Color.WHITE else Color.BLACK)
            binding.textRus1.isVisible=true
            binding.textRus2.isVisible=true
            binding.horizontalScrollView3.isVisible=true
            binding.horizontalScrollView2.isVisible=if (!isButton1Clicked) true else false
            binding.horizontalScrollView.isVisible=if (!isButton1Clicked) true else false
            binding.textGerman1.isVisible=if (!isButton1Clicked) true else false
            binding.textGerman2.isVisible=if (!isButton1Clicked) true else false
            binding.textEng1.isVisible=if (!isButton1Clicked) true else false
            binding.textEng2.isVisible=if (!isButton1Clicked) true else false

        }
        binding.account.setOnClickListener {
            navControl.navigate(R.id.action_homeFragment_to_accountFragment)
        }
        binding.elementaryLevel.setOnClickListener {
            navControl.navigate(R.id.action_homeFragment_to_englishElemFragment)
        }
        binding.beginner.setOnClickListener {
            navControl.navigate(R.id.action_homeFragment_to_englishBeginnerFragment)
        }
        binding.german11.setOnClickListener {
            navControl.navigate(R.id.action_homeFragment_to_germanFisrtFragment)
        }
        binding.german12.setOnClickListener {
            navControl.navigate(R.id.action_homeFragment_to_germanASecFragment)
        }
        binding.espelling.setOnClickListener {
            navControl.navigate(R.id.action_homeFragment_to_russianEFragment)
        }
        binding.conjugation.setOnClickListener {
            navControl.navigate(R.id.action_homeFragment_to_quizFragment)
        }
    }
}