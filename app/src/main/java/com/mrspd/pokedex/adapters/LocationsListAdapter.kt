package com.mrspd.pokedex.adapters

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.mrspd.pokedex.R
import com.mrspd.pokedex.adapters.models.modellocation.LocationResponse
import kotlinx.android.synthetic.main.pokedex_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class LocationsListAdapter(private val itemList: ArrayList<LocationResponse> = arrayListOf()) :
    RecyclerView.Adapter<LocationsListAdapter.ItemViewHolder>(), Filterable {

    var ALlLocations = ArrayList<LocationResponse>()


    override fun getFilter(): Filter {
        return object : Filter() {

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    ALlLocations = itemList
                } else {
                    val resultList = ArrayList<LocationResponse>()
                    for (row in itemList) {
                        if (row.name.toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    ALlLocations = resultList
                }

                val filterResults = FilterResults()
                filterResults.values = ALlLocations

                return filterResults

            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                ALlLocations = results?.values as ArrayList<LocationResponse>
                notifyDataSetChanged()
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.items_locations, parent, false)
        return ItemViewHolder(view)
    }


    fun updateNameList(newItem: LocationResponse) {
        itemList.add(newItem)
        ALlLocations = itemList
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return ALlLocations.size
    }


    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        //set the views with the data
        val item = ALlLocations[position]


        holder.itemView.relativeLayoutBackground.background.colorFilter =
            PorterDuffColorFilter(R.color.purple, PorterDuff.Mode.SRC_ATOP)
        holder.view.textViewName.text = item.name

    }


    class ItemViewHolder(var view: View) : RecyclerView.ViewHolder(view)

}