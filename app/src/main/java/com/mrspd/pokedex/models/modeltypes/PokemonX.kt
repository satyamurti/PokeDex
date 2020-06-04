package com.mrspd.pokedex.adapters.models.modeltypes

import android.os.Parcel
import android.os.Parcelable

data class PokemonX(
    val name: String?,
    val url: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PokemonX> {
        override fun createFromParcel(parcel: Parcel): PokemonX {
            return PokemonX(parcel)
        }

        override fun newArray(size: Int): Array<PokemonX?> {
            return arrayOfNulls(size)
        }
    }
}
