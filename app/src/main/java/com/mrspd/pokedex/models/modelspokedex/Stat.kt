package com.mrspd.pokedex.adapters.models.modelspokedex

import android.os.Parcel
import android.os.Parcelable

data class Stat(
    val base_stat: String?,
//    val effort: Int,
    val stat: StatX?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readParcelable(Stat::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(base_stat)
        parcel.writeParcelable(stat, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Stat> {
        override fun createFromParcel(parcel: Parcel): Stat {
            return Stat(parcel)
        }

        override fun newArray(size: Int): Array<Stat?> {
            return arrayOfNulls(size)
        }
    }
}
