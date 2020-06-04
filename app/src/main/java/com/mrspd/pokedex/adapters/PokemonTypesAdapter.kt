package com.mrspd.pokedex.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.mrspd.pokedex.R
import com.mrspd.pokedex.adapters.models.modeltypes.Pokemon
import kotlinx.android.synthetic.main.poke_abilities_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class PokemonTypesAdapter(private val itemList: ArrayList<Pokemon> = arrayListOf()) :
    RecyclerView.Adapter<PokemonTypesAdapter.ItemViewHolder>(), Filterable {

    var ALlPokemons = ArrayList<Pokemon>()


    override fun getFilter(): Filter {
        return object : Filter() {

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    ALlPokemons = itemList
                } else {
                    val resultList = ArrayList<Pokemon>()
                    for (row in itemList) {
                        if (row.pokemon?.name?.toLowerCase(Locale.ROOT)
                                ?.contains(charSearch.toLowerCase(Locale.ROOT))!!
                        ) {
                            resultList.add(row)
                        }
                    }
                    ALlPokemons = resultList
                }

                val filterResults = FilterResults()
                filterResults.values = ALlPokemons

                return filterResults

            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                ALlPokemons = results?.values as ArrayList<Pokemon>
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.poke_abilities_item, parent, false)
        return ItemViewHolder(view)
    }


    fun updateNameList(newItemList: List<Pokemon>) {
        itemList.addAll(newItemList)
        ALlPokemons = itemList
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return ALlPokemons.size
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        //set the views with the data
        holder.view.pokemon_abilities_list_item_text.text = ALlPokemons[position].pokemon?.name

    }

    class ItemViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}