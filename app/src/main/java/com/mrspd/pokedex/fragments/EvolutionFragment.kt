package com.mrspd.pokedex.fragments

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mrspd.pokedex.R
import com.mrspd.pokedex.models.modelsevolution.EvolutionResponse
import com.mrspd.pokedex.models.modelspokedex.Pokeresponse
import com.mrspd.pokedex.ui.MainActivity
import com.mrspd.pokedex.viewmodel.PokeViewModel
import kotlinx.android.synthetic.main.fragment_evolution.view.*
import kotlin.math.ceil

class EvolutionFragment : Fragment(R.layout.fragment_evolution) {
    lateinit var pokemon: Pokeresponse
    var evoltionList = ArrayList<String>()

    private lateinit var viewModel: PokeViewModel
    private var counter = 2
    private var idd = 0

    private val evolutionObserver = androidx.lifecycle.Observer<EvolutionResponse> {
        if (it != null) {
            viewModel.getEvolutionList()
        }
    }
    private val liveNumberObserver = androidx.lifecycle.Observer<Int> {
        if (it == 27) {
            evoltionList = viewModel.getMyEvolutionList()
            if (!evoltionList.isEmpty()) {
                view?.let { it1 -> setEvolutins(view = it1) }
            }

        }
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
        viewModel.liveNumber.observe(this, liveNumberObserver)
        arguments?.let {
            pokemon = PokemonPodexFragmentArgs.fromBundle(it).pokemon
            idd = pokemon.id
            setIdCall()
        }

    }

    private fun setIdCall() {
        if (idd == 1) {
            viewModel.refresh6(1)
            d("Calucaltions", "LOL")
        } else {
            val num: Float = idd.toFloat()
            val callNumber = ceil((num / 3)).toInt()

            viewModel.refresh6(callNumber)
            d("Calucaltions", "$callNumber")
        }

    }

    private fun setEvolutins(view: View) {
        d("hii", "Yes You are in setEvolutions !!")
        view.tvEvolution1.text = evoltionList[0]
        view.tvEvolution2.text = evoltionList[1]
        view.tvEvolution3.text = evoltionList[2]


    }
}
