package com.example.a9.di.room.db

import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.a9.databinding.ListItemBinding
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteAdapter @Inject constructor():RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    private lateinit var binding: ListItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        binding = ListItemBinding.inflate(inflater,parent,false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ViewHolder:RecyclerView.ViewHolder(binding.root){
        fun setData(item:NoteModel){
            binding.apply {
                textViewItemList.text = "${item.id} : ${item.title}"

                //click
                root.setOnClickListener {
                    onMyItemClicked?.let {
                        it(item)
                    }
                }
            }
        }
    }

    //best way for click on items operation
    private var onMyItemClicked : ((NoteModel) -> Unit)? =  null
    fun whenClickedOnMyItem(listener: (NoteModel) -> Unit) {
        onMyItemClicked = listener
    }
    //

    private val differCallBack = object : DiffUtil.ItemCallback<NoteModel>(){
        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,differCallBack)
}