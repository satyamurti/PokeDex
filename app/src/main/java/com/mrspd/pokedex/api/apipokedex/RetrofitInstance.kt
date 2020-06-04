package com.mrspd.pokedex.api.apipokedex

import com.mrspd.pokedex.models.modelspokedex.Pokeresponse
import com.mrspd.pokedex.util.Constants.Companion.BASE_URL
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {


    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(PokeAPI::class.java)

    fun getMyPokes(number: Int): Single<Pokeresponse> {
        return api.getMyPokes(number.toString())
    }
}
