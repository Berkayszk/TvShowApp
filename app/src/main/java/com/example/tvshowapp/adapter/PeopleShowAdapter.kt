package com.example.tvshowapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.tvshowapp.databinding.TvShowLayoutAdapterBinding
import com.example.tvshowapp.model.PeopleImagesItem
import com.example.tvshowapp.model.TvShowItem

class PeopleShowAdapter : RecyclerView.Adapter<PeopleShowAdapter.MyPeopleHolder>() {

    inner class MyPeopleHolder(val binding : TvShowLayoutAdapterBinding) : ViewHolder(binding.root) {

    }
    private val diffCallback = object : DiffUtil.ItemCallback<PeopleImagesItem>(){
        override fun areItemsTheSame(oldItem: PeopleImagesItem, newItem: PeopleImagesItem): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: PeopleImagesItem, newItem: PeopleImagesItem): Boolean {
            return oldItem==newItem
        }
    }
    private val differ = AsyncListDiffer(this,diffCallback)
    var people:List<PeopleImagesItem>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPeopleHolder {
        return MyPeopleHolder(TvShowLayoutAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyPeopleHolder, position: Int) {
        val currentPeople = people[position]
        holder.binding.apply {
            textView.text = currentPeople.name
            imageView.load(currentPeople.image.original){
                crossfade(true)
                crossfade(1000)
            }
        }

    }

    override fun getItemCount(): Int {
       return people.size
    }
}