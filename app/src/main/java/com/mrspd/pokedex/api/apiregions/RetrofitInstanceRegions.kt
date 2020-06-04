package com.mrspd.pokedex.api.apiregions

import com.mrspd.pokedex.models.modelsregion.RegionsResponse
import com.mrspd.pokedex.util.Constants.Companion.BASE_URL
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstanceRegions {


    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(PokeRegionsAPI::class.java)

    fun getMyPokesRegions(number: Int): Single<RegionsResponse> {
        return api.getMyPokesRegion(number.toString())
    }
}
