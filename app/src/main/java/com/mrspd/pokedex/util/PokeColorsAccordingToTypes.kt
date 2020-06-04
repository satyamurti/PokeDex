package com.mrspd.pokedex.util

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.mrspd.pokedex.R

class PokeColorsAccordingToTypes(var context: Context) {

    @ColorInt
    fun getPokemonColor(typeOfPokemon: String): Int {

        val color = when (typeOfPokemon) {
            "grass", "bug" -> R.color.grass
            "fire" -> R.color.fire
            "water", "fighting", "normal" -> R.color.blue
            "electric", "psychic" -> R.color.yellow
            "poison", "ghost" -> R.color.purple
            "ground", "rock" -> R.color.brown
            "dark" -> R.color.black
            else -> R.color.blue
        }
        return convertColor(color)
    }

    @ColorInt
    fun convertColor(@ColorRes color: Int): Int {
        return ContextCompat.getColor(context, color)
    }
}
