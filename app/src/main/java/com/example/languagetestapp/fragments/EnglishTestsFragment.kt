package com.example.languagetestapp.fragments

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.languagetestapp.R
import com.example.languagetestapp.databinding.FragmentEnglishTestsBinding
import com.example.languagetestapp.databinding.FragmentHomeBinding
import com.google.firebase.database.FirebaseDatabase
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation


class EnglishTestsFragment : Fragment() {
    private lateinit var binding:FragmentEnglishTestsBinding
    lateinit var quizModelList: MutableList<QuizModel>
    private lateinit var navControl: NavController
    lateinit var adapter:QuizListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEnglishTestsBinding.inflate(inflater, container, false)
        quizModelList= mutableListOf()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataFromFirebase()
        init(view)
        binding.back.setOnClickListener {
            navControl.navigate(R.id.action_englishTestsFragment_to_homeFragment)
        }
    }
    private fun init(view: View){
        navControl= Navigation.findNavController(view)
    }
    private fun setupRecView(){
        binding.progressbar.visibility=View.GONE
        adapter= QuizListAdapter(quizModelList)
        binding.recyclerView.layoutManager= LinearLayoutManager(context)
        binding.recyclerView.adapter=adapter
    }
    private fun getDataFromFirebase(){
        binding.progressbar.visibility=View.VISIBLE
        FirebaseDatabase.getInstance().reference
            .get()
            .addOnSuccessListener { dataSnapshot->
                if (dataSnapshot.exists()){
                    for (snap in dataSnapshot.children){
                        val quizModel=snap.getValue(QuizModel::class.java)
                        if (quizModel!=null){
                            quizModelList.add(quizModel)
                        }
                    }
                }
                setupRecView()
            }
    }

}