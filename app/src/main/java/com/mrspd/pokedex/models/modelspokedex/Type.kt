package com.mrspd.pokedex.adapters.models.modelspokedex

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Type(
    val slot: String?,
    @SerializedName("type")
    val type: TypeX?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readParcelable(TypeX::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(slot)
        parcel.writeParcelable(type, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Type> {
        override fun createFromParcel(parcel: Parcel): Type {
            return Type(parcel)
        }

        override fun newArray(size: Int): Array<Type?> {
            return arrayOfNulls(size)
        }
    }
}