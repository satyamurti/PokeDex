package com.mrspd.pokedex.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mrspd.pokedex.R
import com.mrspd.pokedex.adapters.FavoritePokeListAdapter
import com.mrspd.pokedex.models.modelspokedex.Pokeresponse
import com.mrspd.pokedex.ui.MainActivity
import com.mrspd.pokedex.viewmodel.PokeViewModel
import kotlinx.android.synthetic.main.fragment_favorite_pokemon_fragments.*
import kotlinx.android.synthetic.main.fragment_pokedex.*
import kotlinx.android.synthetic.main.fragment_pokedex.searchView


class FavoritePokemonFragments : Fragment() {

    private lateinit var viewModel: PokeViewModel
    private var listAdapter = FavoritePokeListAdapter(arrayListOf())
    private var counter = 1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_pokemon_fragments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        viewModel.getSavedPokes().observe(viewLifecycleOwner, Observer<List<Pokeresponse>> {
            listAdapter.updateNameList(it)
        })
        //configure recyclerview
        recyclerViewfavorites.apply {
            adapter = listAdapter
            layoutManager = GridLayoutManager(context, 1)
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

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val pokeresponse = listAdapter.AllPokeDex[position]
                viewModel.upsert(pokeresponse)
                Snackbar.make(view, "Succesfully Saved To Your Favorites", Snackbar.LENGTH_LONG)
                    .apply {

                        setAction("Undo") {
                            viewModel.upsert(pokeresponse)
                        }
                        show()
                    }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(recyclerView)
        }


    }

}
