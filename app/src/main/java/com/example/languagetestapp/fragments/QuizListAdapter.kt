package com.example.languagetestapp.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.languagetestapp.R
import com.example.languagetestapp.databinding.QuizItemRecViewBinding

class QuizListAdapter(
    private val quizModelList: List<QuizModel>
) : RecyclerView.Adapter<QuizListAdapter.MyViewHolder>() {
    private lateinit var navControl: NavController
    class MyViewHolder(private val binding: QuizItemRecViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: QuizModel) {
            binding.apply {
                quizTitleText.text = model.title
                quizSubtitleText.text = model.subtitle
                quizTimeText.text = model.time + " min"
                root.setOnClickListener {
                    QuizFragment.questionModelList = model.questionList
                    QuizFragment.timer=model.time
                    it.findNavController().navigate(R.id.action_englishTestsFragment_to_quizFragment)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = QuizItemRecViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return quizModelList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(quizModelList[position])

    }
}

