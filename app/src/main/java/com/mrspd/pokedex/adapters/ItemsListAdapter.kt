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
import com.mrspd.pokedex.adapters.models.modelitems.ItemResponse
import com.mrspd.pokedex.util.getProgressDrawable
import com.mrspd.pokedex.util.loadImageUri
import kotlinx.android.synthetic.main.pokedex_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class ItemsListAdapter(private val itemList: ArrayList<ItemResponse> = arrayListOf()) :
    RecyclerView.Adapter<ItemsListAdapter.ItemViewHolder>(), Filterable {
    var ALlItems = ArrayList<ItemResponse>()


    override fun getFilter(): Filter {
        return object : Filter() {

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    ALlItems = itemList
                } else {
                    val resultList = ArrayList<ItemResponse>()
                    for (row in itemList) {
                        if (row.name.toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    ALlItems = resultList
                }

                val filterResults = FilterResults()
                filterResults.values = ALlItems

                return filterResults

            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                ALlItems = results?.values as ArrayList<ItemResponse>
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.pokedex_item, parent, false)
        return ItemViewHolder(view)
    }


    fun updateNameList(newItem: ItemResponse) {
        itemList.add(newItem)
        ALlItems = itemList
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return ALlItems.size
    }


    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        //set the views with the data

        holder.itemView.relativeLayoutBackground.background.colorFilter =
            PorterDuffColorFilter(R.color.red, PorterDuff.Mode.SRC_ATOP)
        holder.view.textViewName.text = ALlItems[position].name
        holder.view.imageView.loadImageUri(
            ALlItems[position].sprites.default,
            getProgressDrawable(holder.view.context)
        )


    }


    class ItemViewHolder(var view: View) : RecyclerView.ViewHolder(view)

}