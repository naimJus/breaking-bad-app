package com.example.thebreakingbadapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thebreakingbadapp.data.Character
import com.example.thebreakingbadapp.databinding.ItemCharacterBinding

class CharacterAdapter(private val viewModel: ListViewModel, var list: List<Character>?) :
    RecyclerView.Adapter<CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder =
        CustomViewHolder(
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            viewModel
        )


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        list?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = list?.size ?: 0
}

class CustomViewHolder(
    private val binding: ItemCharacterBinding,
    private val viewModel: ListViewModel
) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.viewModel = viewModel
    }

    fun bind(character: Character) {
        binding.character = character
    }
}
