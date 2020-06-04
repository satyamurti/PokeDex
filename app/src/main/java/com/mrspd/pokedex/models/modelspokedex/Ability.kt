package com.mrspd.pokedex.adapters.models.modelspokedex

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class Ability(
    @SerializedName("ability")
    val ability: AbilityX?
//    val is_hidden: Boolean,
//    val slot: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable<AbilityX>(
            AbilityX::class.java.classLoader
        )
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(ability, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Ability> {
        override fun createFromParcel(parcel: Parcel): Ability {
            return Ability(parcel)
        }

        override fun newArray(size: Int): Array<Ability?> {
            return arrayOfNulls(size)
        }
    }
}