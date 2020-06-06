package com.mrspd.pokedex.api.apipokedex

import com.mrspd.pokedex.models.modelspecies.SpeciesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface SpeciesAPI {

    @GET("/api/v2/pokemon-species/{pokeNumber}")
    fun getMyPokes(@Path("pokeNumber") pokenumber: String): Single<SpeciesResponse>
}