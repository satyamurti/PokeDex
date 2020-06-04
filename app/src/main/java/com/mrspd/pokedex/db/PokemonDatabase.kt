package com.mrspd.pokedex.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mrspd.pokedex.models.modelspokedex.Pokeresponse


@Database(
    entities = [Pokeresponse::class],
    version = 1
)

@TypeConverters(Conveters::class)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun getPokeDao(): PokeDao


    companion object {

        @Volatile
        private var instace: PokemonDatabase? = null
        private var LOCK = Any()


        operator fun invoke(context: Context) = instace ?: synchronized(LOCK) {

            instace ?: createDatabase(context).also {
                instace = it
            }

        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PokemonDatabase::class.java,
                "pokemon_db"
            ).build()

    }


}