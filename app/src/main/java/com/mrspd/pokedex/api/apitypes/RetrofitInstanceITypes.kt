package com.mrspd.pokedex.api.apitypes

import com.mrspd.pokedex.adapters.models.modeltypes.TypesResponse
import com.mrspd.pokedex.util.Constants.Companion.BASE_URL
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstanceITypes {


    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(PokeTypesAPI::class.java)

    fun getMyPokesTYpes(number: Int): Single<TypesResponse> {
        return api.getMyPokesTypes(number.toString())
    }
}
