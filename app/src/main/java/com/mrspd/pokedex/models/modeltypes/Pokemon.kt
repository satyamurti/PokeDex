package com.mrspd.pokedex.adapters.models.modeltypes

import android.os.Parcel
import android.os.Parcelable

data class Pokemon(
    val pokemon: PokemonX?,
    val slot: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(

        parcel.readParcelable(PokemonX::class.java.classLoader),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(pokemon, flags)
        parcel.writeInt(slot)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pokemon> {
        override fun createFromParcel(parcel: Parcel): Pokemon {
            return Pokemon(parcel)
        }

        override fun newArray(size: Int): Array<Pokemon?> {
            return arrayOfNulls(size)
        }
    }
}