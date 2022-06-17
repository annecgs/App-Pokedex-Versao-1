package com.example.apppokedex.ui.viewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apppokedex.R
import com.example.apppokedex.domain.Pokemon

class PokemonAdapter(
    private val items: List<Pokemon?>
) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.bindView(item)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Pokemon?) = with(itemView) {
            val ivPokemon = findViewById<ImageView>(R.id.pokemonImage)
            val tvNumber = findViewById<TextView>(R.id.numberPokemon)
            val tvName = findViewById<TextView>(R.id.namePokemom)
            val tvType1 = findViewById<TextView>(R.id.tvtype1)
            val tvType2 = findViewById<TextView>(R.id.tvtype2)

            item?.let {
                Glide.with(itemView.context).load(it.imageUrl).into(ivPokemon)

                if (item.number < 10) {
                    tvNumber.text = "Nº 00${item.number}"
                } else if (item.number >= 10 && item.number < 100) {
                    tvNumber.text = "Nº 0${item.number}"
                } else {
                    tvNumber.text = "Nº ${item.number}"
                }

                tvName.text = item.formattedName
                tvType1.text = item.types?.get(0)?.name

                if (item.types.size.toInt() > 1) {
                    tvType2.visibility = View.VISIBLE
                    tvType2.text = item.types?.get(1)?.name
                } else {
                    tvType2.visibility = View.GONE
                }
            }
        }
    }
}
