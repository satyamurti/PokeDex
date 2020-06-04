package com.mrspd.pokedex.models.modelsregion

import android.os.Parcel
import android.os.Parcelable

data class RegionsResponse(
//    val id: Int,
    val locations: List<Location>?,
//    val main_generation: MainGeneration,
    val name: String?,
//    val names: List<Name>,
    val pokedexes: List<Pokedexe>?
//    val version_groups: List<VersionGroup>
) : Parcelable {
    constructor(parcel: Parcel) : this(

        parcel.createTypedArrayList(Location),
        parcel.readString(),
        parcel.createTypedArrayList(Pokedexe)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(locations)
        parcel.writeString(name)
        parcel.writeTypedList(pokedexes)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RegionsResponse> {
        override fun createFromParcel(parcel: Parcel): RegionsResponse {
            return RegionsResponse(parcel)
        }

        override fun newArray(size: Int): Array<RegionsResponse?> {
            return arrayOfNulls(size)
        }
    }
}