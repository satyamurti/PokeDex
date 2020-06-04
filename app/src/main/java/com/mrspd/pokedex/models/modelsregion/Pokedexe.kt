package com.mrspd.pokedex.models.modelsregion

import android.os.Parcel
import android.os.Parcelable

data class Pokedexe(
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

    companion object CREATOR : Parcelable.Creator<Pokedexe> {
        override fun createFromParcel(parcel: Parcel): Pokedexe {
            return Pokedexe(parcel)
        }

        override fun newArray(size: Int): Array<Pokedexe?> {
            return arrayOfNulls(size)
        }
    }
}
