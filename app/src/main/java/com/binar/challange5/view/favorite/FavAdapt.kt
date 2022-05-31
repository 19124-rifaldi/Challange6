package com.binar.challange5.view.favorite

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.binar.challange5.databinding.ItemHomeListBinding
import com.binar.challange5.model.DetailData
import com.binar.challange5.model.Result
import com.binar.challange5.view.home.HomeFragmentDirections
import com.bumptech.glide.Glide


class FavAdapt (val listResult : List<Result>)
    : RecyclerView.Adapter<FavAdapt.ViewHolder>(){

    inner class ViewHolder(private val binding: ItemHomeListBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(data1 : Result){
            binding.apply {
                judulTv.text = data1.title
                ratingTv.text = data1.voteAverage.toString()
                loadImage(itemView,data1.posterPath,filmIv)
                itemId.setOnClickListener{
                    Log.d("TAG", data1.id.toString())

                    val dataku = DetailData(
                        title = data1.title,
                        desc =  data1. overview,
                        photo = data1.posterPath
                    )
                    val move = HomeFragmentDirections.actionHomeFragmentToDetailFragment(dataku)

                    it.findNavController().navigate(move)
                    Log.d("test",dataku.toString())

                }


            }
        }

        private fun loadImage (binding: View, url:String, container : ImageView){
            val urlb = "https://image.tmdb.org/t/p/original/"

            Glide.with(binding)
                .load("$urlb$url")
                .into(container)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHomeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listResult[position])
    }

    override fun getItemCount(): Int =listResult.size

    private val diffCallback = object : DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(
            oldItem: Result,
            newItem: Result
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Result,
            newItem: Result
        ): Boolean = oldItem.hashCode() == newItem.hashCode()
    }

    private val differ = AsyncListDiffer(this,diffCallback)

    fun submitData (value : List<Result>?)= differ.submitList(value)
}