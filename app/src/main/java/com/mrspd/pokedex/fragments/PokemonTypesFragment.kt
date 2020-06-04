package com.mrspd.pokedex.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mrspd.pokedex.R
import com.mrspd.pokedex.adapters.PokemonTypesAdapter
import com.mrspd.pokedex.adapters.models.modeltypes.TypesResponse
import kotlinx.android.synthetic.main.fragment_typespokemon.*

class PokemonTypesFragment : Fragment(R.layout.fragment_typespokemon) {

    var pokemon: TypesResponse? = null

    private var listAdapter = PokemonTypesAdapter(arrayListOf())
    //private var counter = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_typespokemon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            pokemon = PokemonTypesFragmentArgs.fromBundle(it).pokemon


            listAdapter.updateNameList(pokemon!!.pokemon!!)

        }

        //configure recyclerview
        recyclerViewpokemon.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

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