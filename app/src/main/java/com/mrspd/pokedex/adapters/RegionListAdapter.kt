package com.mrspd.pokedex.adapters

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mrspd.pokedex.R
import com.mrspd.pokedex.fragments.RegionFragmentDirections
import com.mrspd.pokedex.models.modelsregion.RegionsResponse
import kotlinx.android.synthetic.main.pokedex_item.view.*

class RegionListAdapter(private val itemList: ArrayList<RegionsResponse> = arrayListOf()) :
    RecyclerView.Adapter<RegionListAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.items_locations, parent, false)
        return ItemViewHolder(view)
    }

    fun updateNameList(newItem: RegionsResponse) {
        itemList.add(newItem)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        //set the views with the data

        holder.itemView.relativeLayoutBackground.background.colorFilter =
            PorterDuffColorFilter(R.color.red, PorterDuff.Mode.SRC_ATOP)
        holder.view.textViewName.text = itemList[position].name
//        holder.view.imageView.loadImageUri(
//            itemList[position].sprites!!.default,
//            getProgressDrawable(holder.view.context)
//        )
        holder.itemView.setOnClickListener {
            val action =
                RegionFragmentDirections.actionRegionFragment2ToPokemonRegoinsFragment(itemList[position])
            Navigation.findNavController(holder.view)
                .navigate(action)
        }
    }

    class ItemViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}