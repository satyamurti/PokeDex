package com.mrspd.pokedex.api.apiitems

import com.mrspd.pokedex.adapters.models.modelitems.ItemResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeItemsAPI {

    @GET("/api/v2/item/{pokeNumber}")
    fun getMyPokes(@Path("pokeNumber") pokenumber: String): Single<ItemResponse>
}