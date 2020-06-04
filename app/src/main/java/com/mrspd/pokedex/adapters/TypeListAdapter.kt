package com.mrspd.pokedex.adapters

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mrspd.pokedex.R
import com.mrspd.pokedex.adapters.models.modeltypes.TypesResponse
import com.mrspd.pokedex.fragments.TypesFragmentDirections
import kotlinx.android.synthetic.main.pokedex_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class TypeListAdapter(private val itemList: ArrayList<TypesResponse> = arrayListOf()) :
    RecyclerView.Adapter<TypeListAdapter.ItemViewHolder>(), Filterable {
    var AllTypes = ArrayList<TypesResponse>()


    override fun getFilter(): Filter {
        return object : Filter() {

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    AllTypes = itemList
                } else {
                    val resultList = ArrayList<TypesResponse>()
                    for (row in itemList) {
                        if (row.name?.toLowerCase(Locale.ROOT)
                                ?.contains(charSearch.toLowerCase(Locale.ROOT))!!
                        ) {
                            resultList.add(row)
                        }
                    }
                    AllTypes = resultList
                }

                val filterResults = FilterResults()
                filterResults.values = AllTypes

                return filterResults

            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                AllTypes = results?.values as ArrayList<TypesResponse>
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.items_locations, parent, false)
        return ItemViewHolder(view)
    }

    fun updateNameList(newItem: TypesResponse) {
        itemList.add(newItem)
        AllTypes = itemList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return AllTypes.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        //set the views with the data

        holder.itemView.relativeLayoutBackground.background.colorFilter =
            PorterDuffColorFilter(R.color.red, PorterDuff.Mode.SRC_ATOP)
        holder.view.textViewName.text = AllTypes[position].name

        holder.itemView.setOnClickListener {
            val action =
                TypesFragmentDirections.actionTypesFragmentToPokemonTypesFragment(AllTypes[position])
            Navigation.findNavController(holder.view)
                .navigate(action)
        }
    }

    class ItemViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}