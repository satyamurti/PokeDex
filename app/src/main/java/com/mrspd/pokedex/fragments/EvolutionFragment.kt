package com.mrspd.pokedex.fragments

import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mrspd.pokedex.R
import com.mrspd.pokedex.models.modelsevolution.EvolutionResponse
import com.mrspd.pokedex.models.modelspecies.EvolutionChain
import com.mrspd.pokedex.models.modelspecies.SpeciesResponse
import com.mrspd.pokedex.models.modelspokedex.Pokeresponse
import com.mrspd.pokedex.ui.MainActivity
import com.mrspd.pokedex.viewmodel.PokeViewModel
import kotlinx.android.synthetic.main.fragment_evolution.view.*

class EvolutionFragment : Fragment(R.layout.fragment_evolution) {
    lateinit var pokemon: Pokeresponse
    lateinit var evolutions: EvolutionResponse

    private lateinit var viewModel: PokeViewModel
    private var idd = 0

    private val evolutionObserver = androidx.lifecycle.Observer<EvolutionResponse> {
        if (it != null) {
            evolutions = it
            setEvolutins(view = this.view!!)
            d("hii", "evolutionObserver")

        }
    }
    private val species = androidx.lifecycle.Observer<SpeciesResponse> {
        if (it != null) {
            callevolutoinIDwork(it)
            d("hii", "callevolutoinIDwork")
        }

    }

    private fun callevolutoinIDwork(it: SpeciesResponse) {

        getevolutionId(it.evolution_chain)
    }

    private fun getevolutionId(evolutionChain: EvolutionChain) {
        viewModel.refresh7(evolutionChain.url.toString())
        d("hii", "${evolutionChain.url}")
    }

    ///////////////////////////////////////////////////////////////////////////
    //   private val loadingObserver = androidx.lifecycle.Observer<Boolean> { isLoading ->
    //        if (isLoading) {
    ////            loading_progressbar.visibility = View.VISIBLE
    ////            listError_textView.visibility = View.GONE
    //
    //        } else {
    ////            loading_progressbar.visibility = View.GONE
    //        }
    //    }
    //    private val listErrorObserver = androidx.lifecycle.Observer<Boolean> { isError ->
    //        if (isError) {
    ////            listError_textView.visibility = View.VISIBLE
    ////            pokedex_recyclerView.visibility = View.GONE
    //        } else {
    ////            listError_textView.visibility = View.GONE
    //        }
    //    }
    ///////////////////////////////////////////////////////////////////////////


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_evolution, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        viewModel.evolutions.observe(this, evolutionObserver)
        viewModel.species.observe(this, species)

        arguments?.let {
            pokemon = PokemonPodexFragmentArgs.fromBundle(it).pokemon
            idd = pokemon.id
            getspecies(idd)
            Log.d("hii", "getspecies")
        }

    }

    private fun getspecies(idd: Int) {
        viewModel.getEvolutionIDFRomSpecies(idd)
    }


    private fun setEvolutins(view: View) {
        d("hii", "Yes You are in setEvolutions !!")
        view.tvEvolution1.text = evolutions.chain.species.name
        if (evolutions.chain.evolves_to.size >= 1) {
            view.tvEvolution2.text = evolutions.chain.evolves_to.get(0).species.name
        }
        if (evolutions.chain.evolves_to.get(0).evolves_to.size >= 1) {
            view.tvEvolution3.text =
                evolutions.chain.evolves_to.get(0).evolves_to.get(0).species.name
        }


    }
}
