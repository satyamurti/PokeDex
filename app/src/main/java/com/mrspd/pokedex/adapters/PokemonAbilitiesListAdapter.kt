package com.mrspd.pokedex.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.mrspd.pokedex.R
import com.mrspd.pokedex.adapters.models.modelspokedex.Ability
import kotlinx.android.synthetic.main.poke_abilities_item.view.*


class PokemonAbilitiesListAdapter(private val itemList: ArrayList<Ability> = arrayListOf()) :
    RecyclerView.Adapter<PokemonAbilitiesListAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.poke_abilities_item, parent, false)
        return ItemViewHolder(view)
    }


    fun updateNameList(newItemList: List<Ability>) {
        itemList.addAll(newItemList)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return itemList.size
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        //set the views with the data
        holder.view.pokemon_abilities_list_item_text.text = itemList[position].ability?.name

    }

    class ItemViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}