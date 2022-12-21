package com.example.tvshowapp.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.tvshowapp.databinding.ActivityMainBinding
import com.example.tvshowapp.databinding.TvShowLayoutAdapterBinding
import com.example.tvshowapp.model.TvShowItem

class TvShowAdapter :RecyclerView.Adapter<TvShowAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: TvShowLayoutAdapterBinding): ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<TvShowItem>(){
        override fun areItemsTheSame(oldItem: TvShowItem, newItem: TvShowItem): Boolean {
           return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: TvShowItem, newItem: TvShowItem): Boolean {
           return oldItem==newItem
        }
    }
    private val differ = AsyncListDiffer(this,diffCallback)
    var tvShows:List<TvShowItem>
    get() = differ.currentList
    set(value) {
        differ.submitList(value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(TvShowLayoutAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentTvShow = tvShows[position]

        holder.binding.apply {
            textView.text = currentTvShow.name
            imageView.load(currentTvShow.image.original){
                crossfade(true)
                crossfade(1000)
            }
        }
    }

    override fun getItemCount(): Int {
        return tvShows.size
    }
}