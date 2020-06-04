package com.mrspd.pokedex.api.apipokedex

import com.mrspd.pokedex.models.modelspokedex.Pokeresponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeAPI {

    @GET("/api/v2/pokemon/{pokeNumber}")
    fun getMyPokes(@Path("pokeNumber") pokenumber: String): Single<Pokeresponse>
}