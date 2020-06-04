package com.mrspd.pokedex.api.apilocations

import com.mrspd.pokedex.adapters.models.modellocation.LocationResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeLocationsAPI {

    @GET("/api/v2/location/{pokeNumber}")
    fun getMyPokesLocations(@Path("pokeNumber") pokenumber: String): Single<LocationResponse>
}