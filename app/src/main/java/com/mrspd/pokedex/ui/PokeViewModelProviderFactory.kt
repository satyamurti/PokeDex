package com.mrspd.pokedex.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mrspd.pokedex.repository.PokemonRepository
import com.mrspd.pokedex.viewmodel.PokeViewModel


class PokeViewModelProviderFactory(
    var newsRepository: PokemonRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PokeViewModel(newsRepository) as T
    }
}