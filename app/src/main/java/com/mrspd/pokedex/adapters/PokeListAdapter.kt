package com.mrspd.pokedex.adapters

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mrspd.pokedex.R
import com.mrspd.pokedex.fragments.PokedexFragmentDirections
import com.mrspd.pokedex.models.modelspokedex.Pokeresponse
import com.mrspd.pokedex.util.PokeColorsAccordingToTypes
import com.mrspd.pokedex.util.getProgressDrawable
import com.mrspd.pokedex.util.loadImageUri
import kotlinx.android.synthetic.main.pokedex_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class PokeListAdapter(private val itemList: ArrayList<Pokeresponse> = arrayListOf()) :
    RecyclerView.Adapter<PokeListAdapter.ItemViewHolder>(), Filterable {
    var AllPokeDex = ArrayList<Pokeresponse>()

    ///////////////////////////////////////////////////////////////////////////
//  //Creating custom callback of articles by using DiffUtil
//  Will use in future :)
//    private val differCallback = object : DiffUtil.ItemCallback<Pokeresponse>() {
//        override fun areItemsTheSame(oldItem: Pokeresponse, newItem: Pokeresponse): Boolean {
//            return oldItem.name == newItem.name
//        }
//        override fun areContentsTheSame(oldItem: Pokeresponse, newItem: Pokeresponse): Boolean {
//            return oldItem == newItem
//        }
//
//    }
//    val differ = AsyncListDiffer(this@PokeListAdapter, differCallback)
///////////////////////////////////////////////////////////////////////////


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


    fun updateNameList(newItem: Pokeresponse) {
        itemList.add(newItem)
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

                val action =
                    PokedexFragmentDirections.actionPokedexFragmentToPokemonFragment(AllPokeDex[position])

                Navigation.findNavController(holder.view).navigate(action)
            }
        }

    }


    class ItemViewHolder(var view: View) : RecyclerView.ViewHolder(view)

}