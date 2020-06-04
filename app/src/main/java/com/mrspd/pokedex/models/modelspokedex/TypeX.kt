package com.mrspd.pokedex.adapters.models.modelspokedex

import android.os.Parcel
import android.os.Parcelable

data class TypeX(
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

    companion object CREATOR : Parcelable.Creator<TypeX> {
        override fun createFromParcel(parcel: Parcel): TypeX {
            return TypeX(parcel)
        }

        override fun newArray(size: Int): Array<TypeX?> {
            return arrayOfNulls(size)
        }
    }
}
