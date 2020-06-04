package com.mrspd.pokedex.api.apitypes

import com.mrspd.pokedex.adapters.models.modeltypes.TypesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeTypesAPI {

    @GET("/api/v2/type/{pokeNumber}")
    fun getMyPokesTypes(@Path("pokeNumber") pokenumber: String): Single<TypesResponse>
}