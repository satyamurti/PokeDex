package com.mrspd.pokedex.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mrspd.pokedex.R
import com.mrspd.pokedex.adapters.PokemonRegionAdapter
import com.mrspd.pokedex.models.modelsregion.RegionsResponse
import kotlinx.android.synthetic.main.fragment_regionspokemon.*
import kotlinx.android.synthetic.main.fragment_typespokemon.recyclerViewpokemon

class PokemonRegoinsFragment : Fragment(R.layout.fragment_regionspokemon) {

    var pokemon: RegionsResponse? = null

    private var listAdapter = PokemonRegionAdapter(arrayListOf())
    //private var counter = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_regionspokemon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            pokemon = PokemonRegoinsFragmentArgs.fromBundle(it).pokemon


            listAdapter.updateNameList(pokemon!!.locations!!)

        }

        //configure recyclerview
        recyclerViewpokemon.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }

        searchViewregions.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                listAdapter.filter.filter(newText)
                return false
            }
        })
    }
}