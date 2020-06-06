package com.mrspd.pokedex.adapters

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mrspd.pokedex.R
import com.mrspd.pokedex.fragments.FavoritePokemonFragmentsDirections
import com.mrspd.pokedex.models.modelspokedex.Pokeresponse
import com.mrspd.pokedex.util.PokeColorsAccordingToTypes
import com.mrspd.pokedex.util.getProgressDrawable
import com.mrspd.pokedex.util.loadImageUri
import kotlinx.android.synthetic.main.pokedex_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class FavoritePokeListAdapter(private val itemList: ArrayList<Pokeresponse> = arrayListOf()) :
    RecyclerView.Adapter<FavoritePokeListAdapter.ItemViewHolder>(), Filterable {
    var AllPokeDex = ArrayList<Pokeresponse>()




    override fun getFilter(): Filter {
        return object : Filter() {

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    AllPokeDex = itemList
                } else {
                    val resultList = ArrayList<Pokeresponse>()
                    for (row in itemList) {
                        if (row.name?.toLowerCase(Locale.ROOT)
                                ?.contains(charSearch.toLowerCase(Locale.ROOT))!!
                        ) {
                            resultList.add(row)
                        }
                    }
                    AllPokeDex = resultList
                }

                val filterResults = FilterResults()
                filterResults.values = AllPokeDex

                return filterResults

            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                AllPokeDex = results?.values as ArrayList<Pokeresponse>
                notifyDataSetChanged()
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.pokedex_item, parent, false)
        return ItemViewHolder(view)
    }


    fun updateNameList(newItem: List<Pokeresponse>) {
        for (i in newItem) {
            itemList.add(i)
        }

        AllPokeDex = itemList

        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return AllPokeDex.size
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        //set the views with the data
        val item = AllPokeDex[position]

        val type = item.types?.get(item.types.size - 1)?.type?.name.toString()
        d("MrSPD", type)
        val color = PokeColorsAccordingToTypes(holder.itemView.context).getPokemonColor(type)
        holder.itemView.relativeLayoutBackground.background.colorFilter =
            PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        holder.view.textViewName.text = item.name
        holder.view.imageView.loadImageUri(
            AllPokeDex[position].sprites!!.front_default,
            getProgressDrawable(holder.view.context)
        )
        holder.itemView.setOnClickListener {

            if (item.name != null) {
                val bundle = Bundle().apply {
                    putString("name", item.name)
                    putString("imageUrl", AllPokeDex[position].sprites!!.front_default)
                }
                val action =
                    FavoritePokemonFragmentsDirections.actionFavoritePokemonFragmentsToPokemonFragment(
                        AllPokeDex[position]
                    )

                Navigation.findNavController(holder.view).navigate(action)
            }
        }

    }


    class ItemViewHolder(var view: View) : RecyclerView.ViewHolder(view)

}