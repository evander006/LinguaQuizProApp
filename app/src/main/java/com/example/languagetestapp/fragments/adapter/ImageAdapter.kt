package com.example.languagetestapp.fragments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.languagetestapp.databinding.ItemImageSliderBinding


class ImageAdapter (private var image:List<Int>):
    RecyclerView.Adapter<ImageAdapter.Pager2ViewHolder>() {

        inner class Pager2ViewHolder(val binding: ItemImageSliderBinding):RecyclerView.ViewHolder(binding.root){
            init {
                binding.ivImage.setOnClickListener {
                    val position = adapterPosition
                }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageAdapter.Pager2ViewHolder {
        val binding=ItemImageSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Pager2ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageAdapter.Pager2ViewHolder, position: Int) {
        holder.binding.ivImage.setImageResource(image[position])
    }

    override fun getItemCount(): Int {
        return image.size
    }


}