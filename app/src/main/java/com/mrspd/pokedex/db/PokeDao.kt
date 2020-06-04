package com.mrspd.pokedex.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mrspd.pokedex.models.modelspokedex.Pokeresponse


@Dao
interface PokeDao {
    //SQL Querris
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(pokeresponse: Pokeresponse): Long


    @Query("SELECT * FROM pokemon")
    fun getMypokes(): LiveData<List<Pokeresponse>>

    @Delete
    suspend fun delletePokemon(pokeresponse: Pokeresponse)
}