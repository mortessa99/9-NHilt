package com.example.a9.di.retrofit.diretrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.a4_nretrofit.model.ResponseMoviesList
import com.example.a9.databinding.ActivityMainBinding.inflate
import com.example.a9.databinding.ItemMovieBinding
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MovieAdapter @Inject constructor(@ApplicationContext context: Context): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private lateinit var binding: ItemMovieBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemMovieBinding.inflate(inflater,parent,false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root){
        fun bind(item:ResponseMoviesList.Data){
            binding.apply {
                txtTitleMovie.text = item.title
                imageViewMovie.load(item.poster)
            }
        }
    }


    private val differCallBack = object : DiffUtil.ItemCallback<ResponseMoviesList.Data>(){
        override fun areItemsTheSame(oldItem: ResponseMoviesList.Data, newItem: ResponseMoviesList.Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResponseMoviesList.Data, newItem: ResponseMoviesList.Data): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,differCallBack)
}