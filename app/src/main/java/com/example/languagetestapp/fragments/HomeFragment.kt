package com.example.languagetestapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.languagetestapp.R
import com.example.languagetestapp.databinding.FragmentHomeBinding
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var navControl: NavController
    lateinit var quizModelList: MutableList<QuizModel>
    lateinit var adapter:QuizListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        quizModelList= mutableListOf()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataFromFirebase()
        init(view)
        regulate()
    }
    private fun init(view: View){
        navControl= Navigation.findNavController(view)
    }
    private fun setupRecView(){
        adapter= QuizListAdapter(quizModelList)
        binding.recyclerView.layoutManager=LinearLayoutManager(context)
        binding.recyclerView.adapter=adapter
    }
    private fun getDataFromFirebase(){
        FirebaseDatabase.getInstance().reference
            .get()
            .addOnSuccessListener {dataSnapshot->
                if(dataSnapshot.exists()){
                    for (snapshot in dataSnapshot.children){
                        val quizModel=snapshot.getValue(QuizModel::class.java)
                        if (quizModel != null) {
                            quizModelList.add(quizModel)
                        }
                    }
                }
                setupRecView()
            }

    }
    private fun regulate(){
        binding.account.setOnClickListener {
            navControl.navigate(R.id.action_homeFragment_to_accountFragment)
        }
    }
}