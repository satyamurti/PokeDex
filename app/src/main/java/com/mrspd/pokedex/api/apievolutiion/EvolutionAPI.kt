package com.mrspd.pokedex.api.apievolutiion

import com.mrspd.pokedex.models.modelsevolution.EvolutionResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface EvolutionAPI {
    @GET
    fun getMyPokes(@Url url: String): Single<EvolutionResponse>
}