package com.mrspd.pokedex.adapters.models.modelitems

data class HeldByPokemon(
    val pokemon: Pokemon,
    val version_details: List<VersionDetail>
)