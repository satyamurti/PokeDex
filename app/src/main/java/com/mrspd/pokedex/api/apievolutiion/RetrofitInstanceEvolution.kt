package com.mrspd.pokedex.api.apievolutiion

import com.mrspd.pokedex.models.modelsevolution.EvolutionResponse
import com.mrspd.pokedex.util.Constants.Companion.BASE_URL
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstanceEvolution {


    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(EvolutionAPI::class.java)

    fun getMyPokesEvolutions(number: Int): Single<EvolutionResponse> {
        return api.getMyPokes(number.toString())
    }
}
