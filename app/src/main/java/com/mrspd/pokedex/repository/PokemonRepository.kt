package com.mrspd.pokedex.repository

import com.mrspd.pokedex.db.PokemonDatabase
import com.mrspd.pokedex.models.modelspokedex.Pokeresponse


class PokemonRepository(
    var db: PokemonDatabase
) {
    suspend fun upsert(pokeresponse: Pokeresponse) = db.getPokeDao().upsert(pokeresponse)
    suspend fun delete(pokeresponse: Pokeresponse) = db.getPokeDao().delletePokemon(pokeresponse)
    fun getMyPokes() = db.getPokeDao().getMypokes()
}