package com.mrspd.pokedex.api.apiitems

import com.mrspd.pokedex.adapters.models.modelitems.ItemResponse
import com.mrspd.pokedex.util.Constants.Companion.BASE_URL
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstanceItems {


    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(PokeItemsAPI::class.java)

    fun getMyPokesItems(number: Int): Single<ItemResponse> {
        return api.getMyPokes(number.toString())
    }
}
