package com.mrspd.pokedex.api.apilocations

import com.mrspd.pokedex.adapters.models.modellocation.LocationResponse
import com.mrspd.pokedex.util.Constants.Companion.BASE_URL
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstanceLocations {


    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(PokeLocationsAPI::class.java)

    fun getMyPokesLocatioins(number: Int): Single<LocationResponse> {
        return api.getMyPokesLocations(number.toString())
    }
}
