package com.example.languagetestapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.languagetestapp.R
import com.example.languagetestapp.databinding.FragmentHomeBinding
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.languagetestapp.fragments.adapter.ImageAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import java.util.UUID

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var navControl: NavController
    private var imageList = mutableListOf<Int>()
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
        //getDataFromFirebase()
        postToList()
        binding.slider.adapter=ImageAdapter(imageList)
        binding.slider.orientation=ViewPager2.ORIENTATION_HORIZONTAL
        binding.sliderDots.setViewPager(binding.slider)
        init(view)
        regulate()
    }
    private fun init(view: View){
        navControl= Navigation.findNavController(view)
    }
    private fun addToList(image:Int){
        imageList.add(image)
    }
    private fun postToList(){
        addToList(R.drawable.slider1)
        addToList(R.drawable.slider2)
        addToList(R.drawable.slider3)
    }
//    private fun setupRecView(){
//        binding.progressbar.visibility=View.GONE
//        adapter= QuizListAdapter(quizModelList)
//        binding.recyclerView.layoutManager=LinearLayoutManager(context)
//        binding.recyclerView.adapter=adapter
//    }
//    private fun getDataFromFirebase(){
//        binding.progressbar.visibility=View.VISIBLE
//        FirebaseDatabase.getInstance().reference
//            .get()
//            .addOnSuccessListener {dataSnapshot->
//                if(dataSnapshot.exists()){
//                    for (snapshot in dataSnapshot.children){
//                        val quizModel=snapshot.getValue(QuizModel::class.java)
//                        if (quizModel != null) {
//                            quizModelList.add(quizModel)
//                        }
//                    }
//                }
//                setupRecView()
//            }
//
//    }
    private fun regulate(){
        binding.account.setOnClickListener {
            navControl.navigate(R.id.action_homeFragment_to_accountFragment)
        }
        binding.engicon.setOnClickListener {
            navControl.navigate(R.id.action_homeFragment_to_englishTestsFragment)
        }
    }
}