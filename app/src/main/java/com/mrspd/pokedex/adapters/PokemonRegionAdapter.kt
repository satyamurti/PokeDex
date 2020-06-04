package com.mrspd.pokedex.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.mrspd.pokedex.R
import com.mrspd.pokedex.models.modelsregion.Location
import kotlinx.android.synthetic.main.poke_abilities_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class PokemonRegionAdapter(private val itemList: ArrayList<Location> = arrayListOf()) :
    RecyclerView.Adapter<PokemonRegionAdapter.ItemViewHolder>(), Filterable {


    var ALlPokemon = ArrayList<Location>()


    override fun getFilter(): Filter {
        return object : Filter() {

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    ALlPokemon = itemList
                } else {
                    val resultList = ArrayList<Location>()
                    for (row in itemList) {
                        if (row.name?.toLowerCase(Locale.ROOT)
                                ?.contains(charSearch.toLowerCase(Locale.ROOT))!!
                        ) {
                            resultList.add(row)
                        }
                    }
                    ALlPokemon = resultList
                }

                val filterResults = FilterResults()
                filterResults.values = ALlPokemon

                return filterResults

            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                ALlPokemon = results?.values as ArrayList<Location>
                notifyDataSetChanged()
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.poke_abilities_item, parent, false)
        return ItemViewHolder(view)
    }


    fun updateNameList(newItemList: List<Location>) {
        itemList.addAll(newItemList)
        ALlPokemon = itemList
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return ALlPokemon.size
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        //set the views with the data
        holder.view.pokemon_abilities_list_item_text.text = ALlPokemon[position].name

    }

    class ItemViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}