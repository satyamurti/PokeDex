package com.mrspd.pokedex.adapters.models.modelspokedex

import android.os.Parcel
import android.os.Parcelable

data class Sprites(
//    val back_default: String,
//    val back_female: Any,
//    val back_shiny: String,
//    val back_shiny_female: Any,
    val front_default: String?
//    val front_female: Any,
//    val front_shiny: String,
//    val front_shiny_female: Any
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(front_default)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Sprites> {
        override fun createFromParcel(parcel: Parcel): Sprites {
            return Sprites(parcel)
        }

        override fun newArray(size: Int): Array<Sprites?> {
            return arrayOfNulls(size)
        }
    }
}


