package com.mrspd.pokedex.api.apievolutiion

import com.mrspd.pokedex.models.modelsevolution.EvolutionResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface EvolutionAPI {
    @GET("/api/v2/evolution-chain/{pokeNumber}")
    fun getMyPokes(@Path("pokeNumber") pokenumber: String): Single<EvolutionResponse>
}