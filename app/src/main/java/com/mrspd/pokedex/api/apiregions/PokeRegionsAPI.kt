package com.mrspd.pokedex.api.apiregions

import com.mrspd.pokedex.models.modelsregion.RegionsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeRegionsAPI {

    @GET("/api/v2/region/{pokeNumber}")
    fun getMyPokesRegion(@Path("pokeNumber") pokenumber: String): Single<RegionsResponse>
}