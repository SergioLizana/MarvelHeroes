package com.test.marvelheroes.view.adapter

import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.marvelheroes.databinding.MarvelComicCellBinding

class MarvelComicAdapter : RecyclerView.Adapter<MarvelComicViewHolder>() {

    private lateinit var list: List<String?>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelComicViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: MarvelComicCellBinding =
            MarvelComicCellBinding.inflate(layoutInflater, parent, false)
        return MarvelComicViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MarvelComicViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setList(list: List<String?>) {
        this.list = list
        notifyDataSetChanged()
    }

    private fun getItem(index: Int): String? = list[index]
}

class MarvelComicViewHolder(val binding: MarvelComicCellBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        comicTitle: String?
    ) {
        comicTitle?.let {
            binding.comicTitle.text = it
        }
    }
}
