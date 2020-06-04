package com.mrspd.pokedex.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.mrspd.pokedex.R
import com.mrspd.pokedex.adapters.PokemonAbilitiesListAdapter
import com.mrspd.pokedex.models.modelspokedex.Pokeresponse
import com.mrspd.pokedex.util.getProgressDrawable
import com.mrspd.pokedex.util.loadImageUri
import kotlinx.android.synthetic.main.fragment_pokemon.*

class PokemonPodexFragment : Fragment(R.layout.fragment_pokemon) {

    lateinit var pokemon: Pokeresponse

    private var listAdapter = PokemonAbilitiesListAdapter(arrayListOf())
    //private var counter = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btEvolutions.setOnClickListener {

            Navigation.findNavController(view).popBackStack(R.id.evolutionFragment, true)
            val action =
                PokemonPodexFragmentDirections.actionPokemonFragmentToEvolutionFragment(pokemon)
            Navigation.findNavController(view).navigate(action)

        }
        btShare.setOnClickListener {

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT,
                    "My Favorite Pokemon is ${pokemon.name} and image url is  ${pokemon.sprites?.front_default}"
                )
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)

        }



        arguments?.let {
            pokemon = PokemonPodexFragmentArgs.fromBundle(it).pokemon
            pokemon_image.loadImageUri(
                pokemon.sprites?.front_default,
                getProgressDrawable(view.context)
            )
            for (stats in pokemon.stats!!) {
                if (stats.stat?.name == "speed") {
                    tvEvolution1.text = "SPEED: ${stats.base_stat}"
                } else if (stats.stat?.name == "attack") {
                    tvEvolution2.text = "ATTACK :  ${stats.base_stat}"
                } else if (stats.stat?.name == "defense") {
                    tvEvolution3.text = "DEFENSE :  ${stats.base_stat}"
                }
            }
            listAdapter.updateNameList(pokemon.abilities!!)

        }

        //configure recyclerview
        abilities_recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }
    }
}