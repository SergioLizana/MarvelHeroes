package com.test.marvelheroes.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marvelheroes.common.utils.getLandscapeImageURL
import com.test.marvelheroes.R
import com.test.marvelheroes.databinding.MarvelListCellBinding
import com.test.marvelheroes.view.model.CharacterDisplay

class MarvelHeroListAdapter(
    private val marvelClick: OnMarvelClickListener
) : RecyclerView.Adapter<MarvelItemViewHolder>() {

    private lateinit var list: List<CharacterDisplay>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: MarvelListCellBinding =
            MarvelListCellBinding.inflate(layoutInflater, parent, false)
        return MarvelItemViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MarvelItemViewHolder, position: Int) {
        holder.bind(getItem(position), marvelClick)
    }

    fun setList(list: List<CharacterDisplay>) {
        this.list = list
        notifyDataSetChanged()
    }

    private fun getItem(index: Int): CharacterDisplay = list?.get(index)
}

class MarvelItemViewHolder(val binding: MarvelListCellBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        characterDisplay: CharacterDisplay,
        listener: OnMarvelClickListener
    ) {
        binding.title.text = characterDisplay.characterName

        Glide.with(binding.root)
            .load(getLandscapeImageURL(characterDisplay.thumbnail?.path,characterDisplay.thumbnail?.extension))
            .placeholder(R.drawable.marvel_placeholder)
            .error(R.drawable.marvel_placeholder)
            .into(binding.marvelListImage);

        binding.root.setOnClickListener {
            characterDisplay.characterId?.let {
                listener.onGoToDetails(it)
            }
        }
    }
}

interface OnMarvelClickListener {
    fun onGoToDetails(marvelId: Int)
}
