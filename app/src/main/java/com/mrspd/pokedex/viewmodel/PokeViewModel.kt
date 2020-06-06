package com.mrspd.pokedex.viewmodel

import android.util.Log.d
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrspd.pokedex.adapters.models.modelitems.ItemResponse
import com.mrspd.pokedex.adapters.models.modellocation.LocationResponse
import com.mrspd.pokedex.adapters.models.modeltypes.TypesResponse
import com.mrspd.pokedex.api.apievolutiion.RetrofitInstanceEvolution
import com.mrspd.pokedex.api.apiitems.RetrofitInstanceItems
import com.mrspd.pokedex.api.apilocations.RetrofitInstanceLocations
import com.mrspd.pokedex.api.apipokedex.RetrofitInstance
import com.mrspd.pokedex.api.apipokedex.RetrofitInstanceSpecies
import com.mrspd.pokedex.api.apiregions.RetrofitInstanceRegions
import com.mrspd.pokedex.api.apitypes.RetrofitInstanceITypes
import com.mrspd.pokedex.models.modelsevolution.EvolutionResponse
import com.mrspd.pokedex.models.modelspecies.SpeciesResponse
import com.mrspd.pokedex.models.modelspokedex.Pokeresponse
import com.mrspd.pokedex.models.modelsregion.RegionsResponse
import com.mrspd.pokedex.repository.PokemonRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class PokeViewModel(var pokemonRepository: PokemonRepository) :
    ViewModel() {
    val evolutions by lazy { MutableLiveData<EvolutionResponse>() }
    val pokemons by lazy { MutableLiveData<Pokeresponse>() }
    val itemss by lazy { MutableLiveData<ItemResponse>() }
    val locations by lazy { MutableLiveData<LocationResponse>() }
    val species by lazy { MutableLiveData<SpeciesResponse>() }
    val types by lazy { MutableLiveData<TypesResponse>() }
    val regions by lazy { MutableLiveData<RegionsResponse>() }
    val loadingError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }


    private val disposable = CompositeDisposable()
    private val apiSeervice1 =
        RetrofitInstance()
    private val apiSeervice2 =
        RetrofitInstanceItems()
    private val apiSeervice3 =
        RetrofitInstanceLocations()
    private val apiSeervice4 =
        RetrofitInstanceITypes()
    private val apiSeervice5 =
        RetrofitInstanceRegions()
    private val apiSeervice6 =
        RetrofitInstanceEvolution()
    private val apiSeervice7 =
        RetrofitInstanceSpecies()
    private var isItemLoaded = true
    private var counter = 1


    fun refresh1(count: Int) {
        counter = 1
        getMyPokemons(count)
        loading.value = true

    }

    fun refresh2(count: Int) {
        counter = 1
        getMyPokesItems(count)
        loading.value = true
    }

    fun refresh3(count: Int) {
        counter = 1
        getMyPokesLocation(count)
        loading.value = true
    }

    fun refresh4(count: Int) {
        counter = 1
        getMyPokesTypes(count)
        loading.value = true
    }

    fun refresh5(count: Int) {
        counter = 1
        getMyPokesRegions(count)
        loading.value = true
    }

    fun refresh7(url: String) {

        getMyPokesEvolution(url)
    }


    private fun getMyPokemons(count: Int) {

        disposable.add(
            apiSeervice1.getMyPokes(count)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Pokeresponse>() {

                    override fun onSuccess(pokemon: Pokeresponse) {
                        pokemons.value = pokemon
                        if (counter < 18) {
                            getSecondPokemon(count + 1)
                            counter += 1
                        }
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        isItemLoaded = false
                        loadingError.value = true
                    }
                })
        )
    }

    private fun getSecondPokemon(number: Int) {
        disposable.add(
            apiSeervice1.getMyPokes(number)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Pokeresponse>() {
                    override fun onSuccess(pokemon: Pokeresponse) {
                        pokemons.value = pokemon
                        if (counter < 19) {
                            getMyPokemons(number + 1)
                            counter += 1
                        }
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        loadingError.value = true
                    }
                })
        )
    }

    fun getMyPokesItems(pokename: Int) {

        disposable.add(
            apiSeervice2.getMyPokesItems(pokename)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ItemResponse>() {
                    override fun onSuccess(items: ItemResponse) {
                        itemss.value = items
                        if (counter < 18) {
                            getSecondItem(pokename + 1)
                            counter += 1
                        }
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        loadingError.value = true
                    }
                })
        )
    }

    private fun getSecondItem(number: Int) {
        disposable.add(
            apiSeervice2.getMyPokesItems(number)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ItemResponse>() {
                    override fun onSuccess(items: ItemResponse) {
                        itemss.value = items
                        if (counter < 19) {
                            getMyPokesItems(number + 1)
                            counter += 1
                        }
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        loadingError.value = true
                    }
                })
        )
    }

    fun getMyPokesLocation(pokename: Int) {

        disposable.add(
            apiSeervice3.getMyPokesLocatioins(pokename)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<LocationResponse>() {
                    override fun onSuccess(items: LocationResponse) {
                        locations.value = items
                        if (counter < 18) {
                            getSecondLocation(pokename + 1)
                            counter += 1
                        }
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        loadingError.value = true
                    }
                })
        )
    }

    private fun getSecondLocation(number: Int) {
        disposable.add(
            apiSeervice3.getMyPokesLocatioins(number)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<LocationResponse>() {
                    override fun onSuccess(items: LocationResponse) {
                        locations.value = items
                        if (counter < 19) {
                            getMyPokesLocation(number + 1)
                            counter += 1
                        }
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        loadingError.value = true
                    }
                })
        )
    }


    fun getMyPokesTypes(pokename: Int) {

        disposable.add(
            apiSeervice4.getMyPokesTYpes(pokename)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<TypesResponse>() {
                    override fun onSuccess(items: TypesResponse) {
                        types.value = items
                        if (counter < 18) {
                            getSecondTypes(pokename + 1)
                            counter += 1
                        }
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        loadingError.value = true
                    }
                })
        )
    }

    private fun getSecondTypes(number: Int) {
        disposable.add(
            apiSeervice4.getMyPokesTYpes(number)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<TypesResponse>() {
                    override fun onSuccess(items: TypesResponse) {
                        types.value = items
                        if (counter < 19) {
                            getMyPokesTypes(number + 1)
                            counter += 1
                        }
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        loadingError.value = true
                    }
                })
        )
    }


    fun getMyPokesRegions(pokename: Int) {

        disposable.add(
            apiSeervice5.getMyPokesRegions(pokename)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<RegionsResponse>() {
                    override fun onSuccess(items: RegionsResponse) {
                        regions.value = items
                        if (counter < 18) {
                            getSecondRegions(pokename + 1)
                            counter += 1
                        }
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        loadingError.value = true
                    }
                })
        )
    }

    private fun getSecondRegions(number: Int) {
        disposable.add(
            apiSeervice5.getMyPokesRegions(number)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<RegionsResponse>() {
                    override fun onSuccess(items: RegionsResponse) {
                        regions.value = items
                        if (counter < 19) {
                            getMyPokesRegions(number + 1)
                            counter += 1
                        }
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        loadingError.value = true
                    }
                })
        )
    }

    fun getEvolutionIDFRomSpecies(number: Int) {
        disposable.add(
            apiSeervice7.getMyEvolutionID(number)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<SpeciesResponse>() {
                    override fun onSuccess(items: SpeciesResponse) {
                        species.value = items

                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        loadingError.value = true
                    }
                })
        )
    }

    fun getMyPokesEvolution(url: String) {

        disposable.add(
            apiSeervice6.getMyPokesEvolutions(url)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<EvolutionResponse>() {
                    override fun onSuccess(items: EvolutionResponse) {
                        d("hii", "Yes Hiii!!")
                        evolutions.value = items
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        loadingError.value = true
                    }
                })
        )
    }


    //Room Liberary
    fun getSavedPokes() = pokemonRepository.getMyPokes()
    fun upsert(pokeresponse: Pokeresponse) = viewModelScope.launch {

        pokemonRepository.upsert(pokeresponse)
    }

    fun delete(pokeresponse: Pokeresponse) = viewModelScope.launch {

        pokemonRepository.delete(pokeresponse)

    }


}